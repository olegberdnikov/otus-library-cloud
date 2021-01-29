package ru.otus.homework.otuslibraryui.util.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.homework.otuslibraryui.dto.AuthorDto;

@Component
public class StringToAuthorDtoConverter
    implements Converter<String, AuthorDto> {
  @Override
  public AuthorDto convert(String s) {
    return new AuthorDto(Long.parseLong(s));
  }
}
