package com.farid.libraryapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableCaching
@EnableScheduling
@EnableAsync
@SpringBootApplication
public class LibraryapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryapiApplication.class, args);
	}

}
