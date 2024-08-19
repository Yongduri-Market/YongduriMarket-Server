package Graduation.work.YongduriMarketServer.service;

import Graduation.work.YongduriMarketServer.domain.Board;
import Graduation.work.YongduriMarketServer.domain.ChatRoom;
import Graduation.work.YongduriMarketServer.domain.User;
import Graduation.work.YongduriMarketServer.dto.ChatRoomRequestDto;
import Graduation.work.YongduriMarketServer.dto.ChatRoomResponseDto;
import Graduation.work.YongduriMarketServer.exception.CustomException;
import Graduation.work.YongduriMarketServer.exception.ErrorCode;
import Graduation.work.YongduriMarketServer.repository.BoardRepository;
import Graduation.work.YongduriMarketServer.repository.ChatRoomRepository;
import Graduation.work.YongduriMarketServer.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;


    //전체조회
    public List<ChatRoomResponseDto> getAllChatRoom()throws Exception{
        List<ChatRoom>  chatRoom = chatRoomRepository.findByOrderByCreatedAtDesc();
        List<ChatRoomResponseDto> getListDTO = new ArrayList<>();
        chatRoom.forEach(s-> getListDTO.add(ChatRoomResponseDto.getChatRoomDTO(s)));
        return getListDTO;
    }

    //상세조회
    public ChatRoomResponseDto getChatRoomDetails(ChatRoomRequestDto.DetailDto request) throws Exception {
        ChatRoom chatRoom = findByRoomId(request.getRoomId());
        try{
            return ChatRoomResponseDto.getChatRoomDTO(chatRoom);
        }catch (Exception e){
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }





    //방 생성
    public Boolean createChatRoom(Long studentId, ChatRoomRequestDto.CreateDto request) {
        User user = findByStudentId(studentId);
        Board board = findByBoardId(request.getBoardId());
        //400 데이터미입력
        if(request.getBoardId() == null || request.getBuyer() == null){
            throw new CustomException(ErrorCode.INSUFFICIENT_DATA);
        }

        try{

            ChatRoom chatRoom = ChatRoom.builder()
                    .board(board)
                    .seller(user)
                    .build();
            chatRoomRepository.save(chatRoom);
            return true;
        }catch (Exception e){
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

    }


    //방 삭제
    public Boolean deleteChatRoom(Long studentId,ChatRoomRequestDto.DeleteDto request) {
        User user = findByStudentId(studentId);
        ChatRoom chatRoom = findByRoomId(request.getRoomId());


        //400 데이터미입력
        if(request.getRoomId() == null){
            throw new CustomException(ErrorCode.INSUFFICIENT_DATA);
        }
        try{
            chatRoomRepository.deleteById(request.getRoomId());
            return true;
        }catch (Exception e){
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

    }


    private User findByStudentId(Long studentId) {
        return userRepository.findByStudentId(studentId)
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_EXIST_MEMBER));
    }
    public ChatRoom findByRoomId(Long roomId) {
        return chatRoomRepository.findByRoomId(roomId)
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_EXIST_ID));
    }

    private Board findByBoardId(Long boardId) {
        return boardRepository.findByBoardId(boardId)
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_EXIST_ID));
    }


}
