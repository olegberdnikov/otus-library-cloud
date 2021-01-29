package ru.otus.homework.otuslibraryui.service;

import java.util.List;
import ru.otus.homework.otuslibraryui.dto.AuthorDto;

public interface AuthorService {
  List<AuthorDto> getAll();

  List<AuthorDto> getAllByIds(List<Long> authorIds);
}
