package ru.otus.homework.otuslibraryui.configs;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages="ru.otus.homework.otuslibraryui.feign")
public class FeignConfig {

}
