package Graduation.work.YongduriMarketServer.controller;

import Graduation.work.YongduriMarketServer.config.CustomUserDetails;
import Graduation.work.YongduriMarketServer.dto.ReportRequestDto;
import Graduation.work.YongduriMarketServer.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor

public class ReportController {
    private final ReportService reportService;

    // report 사용자 조회
    @GetMapping(value = "/report")
    public ResponseEntity<List> getList(@AuthenticationPrincipal CustomUserDetails user) throws Exception {
        return new ResponseEntity<List>(reportService.getList(user.getStudentId()), HttpStatus.OK);
    }
    // report 관리자 조회
    @GetMapping("value = /admin/report")
    public ResponseEntity<List> getAdminList(@AuthenticationPrincipal CustomUserDetails user) throws Exception {
        return new ResponseEntity<List>(reportService.getAdminList(user.getStudentId()), HttpStatus.OK);
    }
    // report 등록
    @PostMapping("/report")
    public ResponseEntity<Boolean> create(@AuthenticationPrincipal CustomUserDetails user, ReportRequestDto.CreateDTO request ) throws Exception {
        System.out.println("report_create request: " + request);
        return new ResponseEntity<Boolean>(reportService.create( user.getStudentId(),request), HttpStatus.OK);
    }
    // report 관리자 답변
    @PostMapping("/admin/repot/answer")
    public ResponseEntity<Boolean> answer(@AuthenticationPrincipal CustomUserDetails user, ReportRequestDto.AnswerDTO request) throws Exception {
        System.out.println("report_answer request: " + request);
        return new ResponseEntity<Boolean>(reportService.answer(user.getStudentId() ,request), HttpStatus.OK);
    }





}