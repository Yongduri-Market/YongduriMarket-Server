package Graduation.work.YongduriMarketServer.service;


import Graduation.work.YongduriMarketServer.domain.Ask;
import Graduation.work.YongduriMarketServer.domain.User;
import Graduation.work.YongduriMarketServer.domain.state.AskCategory;
import Graduation.work.YongduriMarketServer.domain.state.StatusCategory;
import Graduation.work.YongduriMarketServer.dto.AskRequestDto;
import Graduation.work.YongduriMarketServer.dto.AskResponseDto;
import Graduation.work.YongduriMarketServer.exception.CustomException;
import Graduation.work.YongduriMarketServer.exception.ErrorCode;
import Graduation.work.YongduriMarketServer.repository.AskRepository;
import Graduation.work.YongduriMarketServer.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AskService {
    private final AskRepository askRepository;
    private final UserRepository userRepository;


    //ask 조회
    public List<AskResponseDto> getList() throws  Exception {
        List<Ask> ask = askRepository.findByOrderByCreatedAtDesc();
        List<AskResponseDto> getListDto = new ArrayList<>();
        ask.forEach(s->getListDto.add(AskResponseDto.GetAskDTO(s)));
        return getListDto;

    }




    //ask 사용자 등록
    public Boolean create(Long studentId, AskRequestDto.CreateDTO request) {
        User user = findByStudentId(studentId);
        System.out.println(user);


        if (request.getTitle() == null || request.getContents() == null || request.getAskCategory() == null
        )
        {
            throw new CustomException(ErrorCode.INSUFFICIENT_DATA);
        }

        try {

            Ask ask = Ask.builder()
                    .user(user)
                    .title(request.getTitle())
                    .contents(request.getContents())
                    .status(StatusCategory.APPLICATION) // 신청
                    .file(request.getFile())
                    .askCategory(AskCategory.fromInt(request.getAskCategory()))
                    .build();
            askRepository.save(ask);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        return true;
    }



    //ask 관리자 답변
    public Boolean answer(Long studentId, Long askId, AskRequestDto.AnswerDTO request) {

      //  RoleCategory role = RoleCategory.fromInt(request.getRole());
        //404 - id없음
        Ask ask = findByAskId(request.getAskId());
        // 403 - 권한 없음
        User user = findByStudentId(studentId);
      /*  if(user.getRole() !=RoleCategory.MANAGER ){
            throw  new CustomException(ErrorCode.NO_AUTH);
        }

        if (role == RoleCategory.MANAGER) {
            ask.setAnswer(request.getAnswer());
            ask.setStatus(StatusCategory.COMPLETION);
            askRepository.save(ask);
        } else {

            throw new CustomException(ErrorCode.NO_AUTH);
        }
*/
        return true;
    }






    //ask 삭제
    public Boolean delete(Long studentId, AskRequestDto.DeleteDTO request) {
        //404 - id없음
        Ask ask = findByAskId(request.getAskId());
        //403 - 권한 없음  >> 자기가 쓴 글이 아닌경우
        if (!ask.getUser().getStudentId().equals(studentId)) {
            throw new CustomException(ErrorCode.NO_AUTH);
        }
        try{
            askRepository.deleteById(request.getAskId());
            return true;
        }
        catch (Exception e){
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }




    //ask 수정
    public Boolean update( Long studentId,AskRequestDto.UpdateDTO request) {
        AskCategory askCategory = AskCategory.fromInt(request.getAskCategory());
        User user = findByStudentId(studentId);
        //404 - id없음
        Ask ask = findByAskId(request.getAskId());
        //400 - 데이터 미입력
        if(request.getAskId() == null || request.getTitle() == null || request.getContents() == null || request.getAskCategory() == null){
            throw new CustomException(ErrorCode.INSUFFICIENT_DATA);

        }
        //404 - 수정 불가  >> 관리자가 답변을 달아 완료가 된 상태인데 수정하려는 경우
        if(ask.getStatus() == StatusCategory.COMPLETION){
            throw new CustomException(ErrorCode.NOT_MODIFICATION);
        }

        //403 - 권한 없음  >> 자기가 쓴 글이 아닌경우
        if (!ask.getUser().getStudentId().equals(studentId)) {
            throw new CustomException(ErrorCode.NO_AUTH);
        }


        try{

            Optional<Ask> modifyAsk = askRepository.findByAskId(request.getAskId());
            modifyAsk.get().setTitle(request.getTitle());
            modifyAsk.get().setContents(request.getContents());
            modifyAsk.get().setStatus(StatusCategory.APPLICATION);
            modifyAsk.get().setFile(request.getFile());
            modifyAsk.get().setAskCategory(askCategory);
            askRepository.save(ask);


        }catch (Exception e){
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        return true;
    }


    public  Ask findByAskId(Long askId) {
        return askRepository.findByAskId(askId)
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_EXIST_ID));
    }

    public User findByStudentId(Long studentId) {
        return userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_MEMBER));
    }

}
