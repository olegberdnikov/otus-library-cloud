package ru.otus.homework.otuslibraryui.service;

import java.util.List;
import ru.otus.homework.otuslibraryui.dto.CategoryDto;

public interface CategoryService {
  List<CategoryDto> getAll();
}
