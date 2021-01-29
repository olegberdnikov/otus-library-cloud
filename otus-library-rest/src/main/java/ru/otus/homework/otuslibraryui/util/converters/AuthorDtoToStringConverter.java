package ru.otus.homework.otuslibraryui.util.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.homework.otuslibraryui.dto.AuthorDto;

@Component
public class AuthorDtoToStringConverter
    implements Converter<AuthorDto,String> {
  @Override
  public String convert(AuthorDto authorDto) {
    return authorDto.getAuthorId().toString();
  }
}
