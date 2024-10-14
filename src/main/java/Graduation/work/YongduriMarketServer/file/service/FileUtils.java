package Graduation.work.YongduriMarketServer.file.service;

import Graduation.work.YongduriMarketServer.file.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.UUID;

public final class FileUtils {

    private FileUtils() {
    }

    public static String getFilePullPath(FileInfo fileInfo, String rootDirectory) {
        final String fileName = fileInfo.getFileName();
        final String fileUrl = fileInfo.getFileUrl();
        final String fileExt = fileInfo.getFileExt();

        return rootDirectory + fileUrl + "/" + fileName + "." + fileExt;
    }

    public static FileInfo transformMultipart(MultipartFile multipartFile, ImageType imageType) {

        final String randomFileName = UUID.randomUUID().toString();
        final String directoryName = switch (imageType) {
            case CHAT -> "/chat";
            case FILE -> "/file";
            case BOARD -> "/board";
            case USER -> "/user";
            case REPORT -> "/report";
            case PRODUCT -> "/product";
        };
        final String fileExt = extractExt(Objects.requireNonNull(multipartFile.getOriginalFilename()));

        final FileInfo fileInfo = new FileInfo();
        fileInfo.setFileName(randomFileName);
        fileInfo.setFileOriName(multipartFile.getOriginalFilename());
        fileInfo.setFileUrl(directoryName);
        fileInfo.setFileExt(fileExt);
        fileInfo.setFileSize(multipartFile.getSize());

        return fileInfo;
    }

    public static String extractExt(String fileName) {
        final int pos = fileName.lastIndexOf(".");
        return fileName.substring(pos + 1);
    }
}

