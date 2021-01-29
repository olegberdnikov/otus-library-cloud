package ru.otus.homework.otuslibraryui.configs;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class LocalizationConfig {

  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("classpath:i18n/bundle");
    messageSource.setFallbackToSystemLocale(false);
    messageSource.setCacheSeconds(0);
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }

}
