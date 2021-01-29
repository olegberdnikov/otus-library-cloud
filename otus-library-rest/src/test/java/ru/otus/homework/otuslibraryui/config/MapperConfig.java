package ru.otus.homework.otuslibraryui.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.otuslibraryui.util.ServiceMapper;

@Configuration
public class MapperConfig {

  @Bean
  public ServiceMapper mainMapper() {
    return Mappers.getMapper(ServiceMapper.class);
  }
}
