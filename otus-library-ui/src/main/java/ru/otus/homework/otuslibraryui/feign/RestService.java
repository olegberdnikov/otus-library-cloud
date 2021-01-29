package ru.otus.homework.otuslibraryui.feign;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.homework.otuslibraryui.dto.AuthorDto;
import ru.otus.homework.otuslibraryui.dto.CategoryDto;

@FeignClient(name = "${application.feignservce.rest}", fallback = RestServiceFallback.class)
public interface RestService {

  @GetMapping("/authors/")
  List<AuthorDto> getAuthors();

  @GetMapping("/categories/")
  List<CategoryDto> getCategories();

}
