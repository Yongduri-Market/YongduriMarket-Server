package Graduation.work.YongduriMarketServer.file.controller;

import Graduation.work.YongduriMarketServer.file.entity.FileInfo;
import Graduation.work.YongduriMarketServer.file.service.FileService;
import Graduation.work.YongduriMarketServer.file.service.ImageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<FileInfo>> saveFile(
            @RequestParam(value = "imageType", defaultValue = "FILE", required = false) final ImageType imageType,
            @RequestParam(value = "files") List<MultipartFile> files) {

        log.info("files size[{}]", files.size());
        return ResponseEntity.ok(fileService.saveFile(files, imageType));
    }

    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileInfo> uploadFile(@RequestParam("file") MultipartFile file) {
        // 파일 저장 로직 구현
        FileInfo fileInfo = fileService.saveFile(file, ImageType.CHAT);  // 파일 저장 후 파일 정보 반환

        // 파일 정보를 응답으로 반환
        return ResponseEntity.ok(fileInfo);
    }

    @GetMapping("/v1")
    public ResponseEntity<?> getFileV1(@RequestParam(value = "fileId") final Long fileId) {
        log.info("fileId[{}]", fileId);
        return ResponseEntity.ok(fileService.getFileV1(fileId));
    }

}
