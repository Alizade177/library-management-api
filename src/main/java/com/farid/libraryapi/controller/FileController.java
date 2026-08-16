package com.farid.libraryapi.controller;

import com.farid.libraryapi.service.FileService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import java.nio.file.Files;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;


@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @Operation(
            summary = "Upload a file",
            description = "Uploads a PDF, JPG or PNG file."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "File uploaded successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid file type or size"
            )
    })
    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file) {

        String filename =
                fileService.uploadFile(file);

        return ResponseEntity.ok(
                "File uploaded successfully: " + filename
        );
    }

    @Operation(
            summary = "Download a file",
            description = "Downloads a previously uploaded file."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "File downloaded successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "File not found"
            )
    })
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable String filename) {

        Resource resource =
                fileService.downloadFile(filename);

        MediaType contentType;

        try {

            String mimeType =
                    Files.probeContentType(
                            resource.getFile().toPath()
                    );

            contentType = mimeType != null
                    ? MediaType.parseMediaType(mimeType)
                    : MediaType.APPLICATION_OCTET_STREAM;

        } catch (Exception e) {

            contentType =
                    MediaType.APPLICATION_OCTET_STREAM;
        }

        return ResponseEntity.ok()
                .contentType(contentType)
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" +
                                resource.getFilename() +
                                "\""
                )
                .body(resource);
    }
}