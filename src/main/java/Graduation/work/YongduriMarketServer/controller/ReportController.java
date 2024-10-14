package Graduation.work.YongduriMarketServer.controller;
import Graduation.work.YongduriMarketServer.config.CustomUserDetails;
import Graduation.work.YongduriMarketServer.dto.ReportRequestDto;
import Graduation.work.YongduriMarketServer.dto.ReportResponseDto;
import Graduation.work.YongduriMarketServer.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class ReportController {
    private final ReportService reportService;

    // report 사용자 전체 조회
    @GetMapping(value = "/report")
    public ResponseEntity<List> getAllReport() throws Exception {
        return new ResponseEntity<List>(reportService.getAllReport(), HttpStatus.OK);
    }
    // report 사용자 상세 조회
    @GetMapping(value = "/report/detail")
    public ResponseEntity<ReportResponseDto> getUserDetail(@RequestParam Long reportId) throws Exception {
        return new ResponseEntity<ReportResponseDto>(reportService.getUserDetail(reportId), HttpStatus.OK);
    }


    // report 관리자 전체 조회
    @GetMapping(value ="/admin/report")
    public ResponseEntity<List> getAdminReport() throws Exception {
        return new ResponseEntity<List>(reportService.getAdminReport(), HttpStatus.OK);
    }

    // report 관리자 상세 조회
    @GetMapping(value ="/admin/report/detail")
    public ResponseEntity<ReportResponseDto> getAdminDetail(@RequestParam Long reportId) throws Exception {
        return new ResponseEntity<ReportResponseDto>(reportService.getAdminDetail(reportId), HttpStatus.OK);
    }

    // report 유저 신고
    @PostMapping(value="/report/user", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Boolean> reportUser  (@AuthenticationPrincipal CustomUserDetails user, @RequestPart("reportRequest") ReportRequestDto.UserReportDto request,@RequestPart(value = "file", required = false) MultipartFile file ) throws Exception {
        return new ResponseEntity<Boolean>(reportService.reportUser( user.getStudentId(),request, file), HttpStatus.OK);
    }
    // report 앱 버그 신고
    @PostMapping("/report/bug")
    public ResponseEntity<Boolean> reportAppBug (@AuthenticationPrincipal CustomUserDetails user,  @RequestBody  ReportRequestDto.BugReportDto request ) throws Exception {
        return new ResponseEntity<Boolean>(reportService.reportAppBug( user.getStudentId(),request), HttpStatus.OK);
    }

    // report 관리자 답변
    @PostMapping("/admin/report/answer")
    public ResponseEntity<Boolean> answerAdmin(@AuthenticationPrincipal CustomUserDetails user,  @RequestBody ReportRequestDto.AnswerDTO request) throws Exception {
        return new ResponseEntity<Boolean>(reportService.answerAdmin(user.getStudentId() ,request), HttpStatus.OK);
    }





}