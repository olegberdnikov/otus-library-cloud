package ru.otus.homework.otuslibraryui.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.homework.otuslibraryui.configs.YamlProperties;


@Component
public class MessageBundleResourceImpl implements MessageBundleResource {

  private final YamlProperties yamlProperties;
  private final MessageSource messageSource;

  @Autowired
  public MessageBundleResourceImpl(YamlProperties yamlProperties, MessageSource messageSource) {
    this.yamlProperties = yamlProperties;
    this.messageSource = messageSource;
  }

  @Override
  public String getMessage(String messageCode) {
    return messageSource.getMessage(messageCode, null, yamlProperties.getLocale());
  }
  @Override
  public String getMessage(String messageCode, Object... params) {
    return messageSource.getMessage(messageCode, params, yamlProperties.getLocale());
  }

}
