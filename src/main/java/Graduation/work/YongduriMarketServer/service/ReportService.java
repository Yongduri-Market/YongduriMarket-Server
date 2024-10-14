package Graduation.work.YongduriMarketServer.service;
import Graduation.work.YongduriMarketServer.domain.Report;
import Graduation.work.YongduriMarketServer.domain.User;
import Graduation.work.YongduriMarketServer.domain.state.ReportStatus;
import Graduation.work.YongduriMarketServer.domain.state.ReportType;
import Graduation.work.YongduriMarketServer.dto.ReportRequestDto;
import Graduation.work.YongduriMarketServer.dto.ReportResponseDto;
import Graduation.work.YongduriMarketServer.file.entity.FileInfo;
import Graduation.work.YongduriMarketServer.file.service.FileService;
import Graduation.work.YongduriMarketServer.file.service.ImageType;
import Graduation.work.YongduriMarketServer.repository.ReportRepository;
import Graduation.work.YongduriMarketServer.repository.UserRepository;
import Graduation.work.YongduriMarketServer.exception.ErrorCode;
import Graduation.work.YongduriMarketServer.exception.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReportService {
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final FileService fileService;


    public List<ReportResponseDto> getAllReport() throws Exception {
        List<Report> reports = reportRepository.findByOrderByCreatedAtDesc();
        List<ReportResponseDto> reportDtoList = new ArrayList<>();

        reports.forEach(report -> {
            ReportResponseDto reportDto = ReportResponseDto.getReportDto(report);
            if (reportDto.getToUserId() == null) {
                reportDto.setToUserId(0L);
            }
            reportDtoList.add(reportDto);
        });

        return reportDtoList;
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
    public Boolean reportUser(Long studentId, ReportRequestDto.UserReportDto request, MultipartFile file) {
        User user = findByStudentId(studentId);
        User toUserId = findByStudentId(request.getToUserId());

        // 400 -데이터 미입력
        if(request.getReportContents().isEmpty() || request.getToUserId() == null
        || request.getUserReportReason() == null){
            throw new CustomException(ErrorCode.INSUFFICIENT_DATA);
        }

        List<FileInfo> fileInfos = null;

        if(file != null) {
            fileInfos = fileService.saveFile(List.of(file), ImageType.REPORT);
        }

        try{

            Report report = Report.builder()
                    .reportContents(request.getReportContents())
                    .userId(user)
                    .toUserId(toUserId)
                    .reportType(ReportType.유저신고)
                    .reportStatus(ReportStatus.대기중)
                    .fileInfo(fileInfos.get(0))
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
        if(request.getReportContents().isEmpty()  ){
            throw new CustomException(ErrorCode.INSUFFICIENT_DATA);
        }
        try{

            Report report = Report.builder()
                    .userId(user)
                    .toUserId(user)
                    .reportContents(request.getReportContents())
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