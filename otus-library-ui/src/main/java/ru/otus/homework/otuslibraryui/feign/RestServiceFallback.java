package ru.otus.homework.otuslibraryui.feign;

import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;
import ru.otus.homework.otuslibraryui.dto.AuthorDto;
import ru.otus.homework.otuslibraryui.dto.CategoryDto;

@Component
public class RestServiceFallback implements RestService{

  @Override
  public List<AuthorDto> getAuthors() {
    return Collections.emptyList();
  }

  @Override
  public List<CategoryDto> getCategories() {
    return Collections.emptyList();
  }
}
