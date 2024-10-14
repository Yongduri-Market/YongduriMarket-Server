package Graduation.work.YongduriMarketServer.service;

import Graduation.work.YongduriMarketServer.domain.Board;
import Graduation.work.YongduriMarketServer.domain.BoardLike;
import Graduation.work.YongduriMarketServer.domain.User;
import Graduation.work.YongduriMarketServer.dto.BoardResponseDto;
import Graduation.work.YongduriMarketServer.dto.UserResponseDto;
import Graduation.work.YongduriMarketServer.exception.CustomException;
import Graduation.work.YongduriMarketServer.exception.ErrorCode;
import Graduation.work.YongduriMarketServer.file.service.FileService;
import Graduation.work.YongduriMarketServer.file.service.ImageType;
import Graduation.work.YongduriMarketServer.repository.BoardLikeRepository;
import Graduation.work.YongduriMarketServer.repository.BoardRepository;
import Graduation.work.YongduriMarketServer.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final BoardLikeRepository boardLikeRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final FileService fileService;

    // 유저가 좋아요를 누른 게시글 목록 조회
    public List<BoardResponseDto> getLikedBoards(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<BoardLike> boardLikes = boardLikeRepository.findByUser(user);
        List<Board> likedBoards = boardLikes.stream()
                .map(BoardLike::getBoard)
                .collect(Collectors.toList());
        return likedBoards.stream()
                .map(BoardResponseDto::getBoardDto)
                .collect(Collectors.toList());
    }
    // 내 정보 조회
    public UserResponseDto getInfoList(Long studentId) throws Exception{

        User user = findByStudentId(studentId);

        try{
            UserResponseDto userResponseDto = UserResponseDto.builder()
                    .studentId(user.getStudentId())
                    .name(user.getName())
                    .birthDate(user.getBirthDate())
                    .nickname(user.getNickname())
                    .phone(user.getPhone())
                    .build();

            return userResponseDto;
        }catch (Exception e){
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

    }



    // 내 정보 수정
    public Boolean infoUpdate(Long studentId, UserResponseDto request, MultipartFile file)throws Exception {
        //404 studentId 없음
        User user = findByStudentId(studentId);

        // 파일 수정
        user.setFileInfo(fileService.update(user.getFileInfo().getId(), file, ImageType.USER));

        try{
            user.setNickname(request.getNickname());
            userRepository.save(user);
            return true;
        }catch (Exception e){
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }


    public User findByStudentId(Long studentId) {
        return userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_MEMBER));

    }
}
