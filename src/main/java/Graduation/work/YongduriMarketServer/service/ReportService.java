package Graduation.work.YongduriMarketServer.service;

import Graduation.work.YongduriMarketServer.domain.Report;
import Graduation.work.YongduriMarketServer.domain.User;
import Graduation.work.YongduriMarketServer.domain.state.ReportStatus;
import Graduation.work.YongduriMarketServer.dto.ReportRequestDto;
import Graduation.work.YongduriMarketServer.dto.ReportResponseDto;
import Graduation.work.YongduriMarketServer.repository.ReportRepository;
import Graduation.work.YongduriMarketServer.repository.UserRepository;
import Graduation.work.YongduriMarketServer.exception.ErrorCode;
import Graduation.work.YongduriMarketServer.exception.CustomException;
import com.google.firebase.database.core.Repo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class ReportService {
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    public List<ReportResponseDto> getList() throws Exception{
        List<Report> report = reportRepository.findByOrderByCreatedAtDesc();
        List<ReportResponseDto> getListDto = new ArrayList<>();
        report.forEach(s -> getListDto.add(ReportResponseDto.GetReportDto(s)));
        return getListDto;
    }

    public Boolean create(Long studentId, ReportRequestDto.CreateDTO request) throws Exception{
        User user = findByStudentId(studentId);

        // 400 -데이터 미입력
        if(request.getReportContents().isEmpty() ||request.getFileId() ==null){
            throw new CustomException(ErrorCode.INSUFFICIENT_DATA);
        }
        try{

            Report report = Report.builder()

                    .reportContents(request.getReportContents())
                    .fileId(request.getFileId())
                    .user(user)
                    .reportStatus(ReportStatus.접수완료)
                    .build();
            reportRepository.save(report);
            return true;
        }catch (Exception e){
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }




    }

    public Boolean answer(Long studentId,Long reportId,   ReportRequestDto.AnswerDTO request) throws Exception{
        User user = findByStudentId(studentId);

        Report report = findByReportId(reportId);
        // 400 -데이터 미입력
        if(request.getReportId() == null){
            throw new CustomException(ErrorCode.INSUFFICIENT_DATA);
        }
        try{

            report.setReportAnswer(request.getReportAnswer());
            report.setReportStatus(ReportStatus.답변완료);
            return true;
        }catch (Exception e){
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }



    }

    public  Report findByReportId(Long reportId) {
        return reportRepository.findByReportId(reportId)
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_EXIST_ID));
    }

    public User findByStudentId(Long studentId) {
        return userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_MEMBER));
    }
}
