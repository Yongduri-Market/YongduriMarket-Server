package Graduation.work.YongduriMarketServer.service;
import Graduation.work.YongduriMarketServer.domain.Report;
import Graduation.work.YongduriMarketServer.domain.User;
import Graduation.work.YongduriMarketServer.domain.state.ReportStatus;
import Graduation.work.YongduriMarketServer.domain.state.ReportType;
import Graduation.work.YongduriMarketServer.dto.ReportRequestDto;
import Graduation.work.YongduriMarketServer.dto.ReportResponseDto;
import Graduation.work.YongduriMarketServer.dto.SearchResponseDto;
import Graduation.work.YongduriMarketServer.repository.ReportRepository;
import Graduation.work.YongduriMarketServer.repository.UserRepository;
import Graduation.work.YongduriMarketServer.exception.ErrorCode;
import Graduation.work.YongduriMarketServer.exception.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReportService {
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;


    public List<ReportResponseDto> getAllReport() throws Exception {
        List<Report> reports = reportRepository.findByOrderByCreatedAtDesc();
        return reports.stream()
                .map(ReportResponseDto::getReportDto)
                .collect(Collectors.toList());
    }

    //신고하기 사용자 상세조회
    public ReportResponseDto getUserDetail(Long reportId)  throws Exception{
        Report report  = findByReportId(reportId);
        try{
            return ReportResponseDto.getReportDto(report);

        }catch (Exception e){
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

    }


    //신고하기 관리자 전체조회
    public List<ReportResponseDto> getAdminReport() {
        List<Report> report = reportRepository.findByOrderByCreatedAtDesc();
        List<ReportResponseDto> getListDto = new ArrayList<>();
        report.forEach(s -> getListDto.add(ReportResponseDto.getReportDto(s)));
        return getListDto;
    }
    //신고하기 관리자 상세조회
    public ReportResponseDto getAdminDetail(Long reportId)  throws Exception{
        Report report  = findByReportId(reportId);
        try{
            return ReportResponseDto.getReportDto(report);
        }catch (Exception e){
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

    }

    // 유저 신고
    public Boolean reportUser(Long studentId, ReportRequestDto.UserReportDto request) {
        User user = findByStudentId(studentId);
        User toUserId = findByStudentId(request.getToUserId());

        // 400 -데이터 미입력
        if(request.getContents().isEmpty() || request.getToUserId() == null
        || request.getReason() == null ){
            throw new CustomException(ErrorCode.INSUFFICIENT_DATA);
        }
        try{

            Report report = Report.builder()
                    .contents(request.getContents())
                    .userId(user)
                    .toUserId(toUserId)
                    .reason(request.getReason())
                    .reportType(ReportType.유저신고)
                    .reportStatus(ReportStatus.대기중)
                    .build();
            reportRepository.save(report);
            return true;
        }catch (Exception e){
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

    }
    // 앱 버그 신고
    public Boolean reportAppBug(Long studentId, ReportRequestDto.BugReportDto request) {
        User user = findByStudentId(studentId);

        // 400 -데이터 미입력
        if(request.getContents().isEmpty()  ){
            throw new CustomException(ErrorCode.INSUFFICIENT_DATA);
        }
        try{

            Report report = Report.builder()
                    .userId(user)
                    .toUserId(user)
                    .contents(request.getContents())
                    .reportType(ReportType.앱버그신고)
                    .reportStatus(ReportStatus.대기중)
                    .build();
            reportRepository.save(report);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }







    //신고하기 관리자 답변
    public Boolean answerAdmin(Long studentId, ReportRequestDto.AnswerDTO request) throws Exception{
        User user = findByStudentId(studentId);
        // 400 -데이터 미입력
        if(request.getReportId() == null){
            throw new CustomException(ErrorCode.INSUFFICIENT_DATA);
        }
        Report report = findByReportId(request.getReportId());
        try{
            report.setAnswer(request.getAnswer());
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