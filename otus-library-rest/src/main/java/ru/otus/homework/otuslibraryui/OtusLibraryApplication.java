package ru.otus.homework.otuslibraryui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OtusLibraryApplication {
	public static void main(String[] args) {
		SpringApplication.run(OtusLibraryApplication.class, args);
	}
}
