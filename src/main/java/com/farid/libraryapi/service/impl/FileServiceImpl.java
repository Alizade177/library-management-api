package com.farid.libraryapi.service.impl;

import com.farid.libraryapi.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    private final Path uploadPath;
    private final long maxFileSize;

    public FileServiceImpl(
            @Value("${file.upload-dir}") String uploadDir,
            @Value("${file.max-size}") long maxFileSize) {

        this.uploadPath = Paths.get(uploadDir)
                .toAbsolutePath()
                .normalize();

        this.maxFileSize = maxFileSize;

        try {
            Files.createDirectories(this.uploadPath);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Could not create upload directory",
                    e
            );
        }
    }
    @Override
    public String uploadFile(MultipartFile file) {

        if (file.isEmpty()) {
            throw new IllegalArgumentException(
                    "File cannot be empty"
            );
        }

        if (file.getSize() > maxFileSize) {
            throw new IllegalArgumentException(
                    "File size cannot exceed 5 MB"
            );
        }

        String contentType = file.getContentType();

        if (contentType == null ||
                !ALLOWED_TYPES.contains(contentType)) {

            throw new IllegalArgumentException(
                    "Only JPG, PNG and PDF files are allowed"
            );
        }

        try {

            String filename = Paths
                    .get(file.getOriginalFilename())
                    .getFileName()
                    .toString();

            Path targetLocation =
                    uploadPath.resolve(filename)
                            .normalize();

            if (!targetLocation.startsWith(uploadPath)) {
                throw new IllegalArgumentException(
                        "Invalid file name"
                );
            }

            Files.copy(
                    file.getInputStream(),
                    targetLocation,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return filename;

        } catch (IOException e) {

            throw new RuntimeException(
                    "Could not store file",
                    e
            );
        }
    }

    @Override
    public Resource downloadFile(String filename) {

        try {

            Path filePath =
                    uploadPath.resolve(filename)
                            .normalize();

            Resource resource =
                    new UrlResource(
                            filePath.toUri()
                    );

            if (!resource.exists()) {
                throw new RuntimeException(
                        "File not found"
                );
            }

            return resource;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Could not read file",
                    e
            );
        }
    }

    private static final List<String> ALLOWED_TYPES = List.of(
            "image/jpeg",
            "image/png",
            "application/pdf"
    );

}