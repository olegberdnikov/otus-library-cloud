package ru.otus.homework.otuslibraryui.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.homework.otuslibraryui.configs.LocalizationConfig;
import ru.otus.homework.otuslibraryui.configs.YamlProperties;
import ru.otus.homework.otuslibraryui.util.MessageBundleResourceImpl;

@ExtendWith(SpringExtension.class)
@DisplayName("Получение локалей")
@Import({LocalizationConfig.class, YamlProperties.class})
class MessageBundleResourceImplTest {

  private @Autowired MessageSource messageSource;
  private YamlProperties yamlProperties = new YamlProperties();
  private MessageBundleResourceImpl messageBundleResource;

  @Test
  void getMessageRu() {
    yamlProperties.setLocale(new Locale("ru_RU"));
    messageBundleResource = new MessageBundleResourceImpl(yamlProperties, messageSource);
    final String message = messageBundleResource.getMessage("home.welcome");
    assertThat(message).isNotNull();
  }

  @Test
  void getMessageEn() {
    yamlProperties.setLocale(Locale.ENGLISH);
    messageBundleResource = new MessageBundleResourceImpl(yamlProperties, messageSource);
    final String message = messageBundleResource.getMessage("app.input-command-name");
    assertThat(message).isNotNull();
  }

}