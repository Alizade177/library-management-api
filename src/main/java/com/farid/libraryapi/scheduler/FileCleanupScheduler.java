package com.farid.libraryapi.scheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

@Component
public class FileCleanupScheduler {

    private final Path uploadPath;
    private final long expirationDays;

    public FileCleanupScheduler(
            @Value("${file.upload-dir}") String uploadDir,
            @Value("${file.expiration-days}") long expirationDays) {

        this.uploadPath = Paths.get(uploadDir)
                .toAbsolutePath()
                .normalize();

        this.expirationDays = expirationDays;
    }

    @Scheduled(
            fixedDelayString = "${file.cleanup.interval}"
    )
    public void cleanupOldFiles() {

        try (Stream<Path> files = Files.list(uploadPath)) {

            Instant expirationTime =
                    Instant.now()
                            .minus(Duration.ofDays(expirationDays));

            files.filter(Files::isRegularFile)
                    .forEach(file -> {

                        try {

                            FileTime lastModified =
                                    Files.getLastModifiedTime(file);

                            if (lastModified
                                    .toInstant()
                                    .isBefore(expirationTime)) {

                                Files.deleteIfExists(file);

                                System.out.println(
                                        "Deleted old file: "
                                                + file.getFileName()
                                );
                            }

                        } catch (IOException e) {

                            System.err.println(
                                    "Could not delete file: "
                                            + file.getFileName()
                            );
                        }
                    });

        } catch (IOException e) {

            System.err.println(
                    "Could not scan upload directory"
            );
        }
    }
}