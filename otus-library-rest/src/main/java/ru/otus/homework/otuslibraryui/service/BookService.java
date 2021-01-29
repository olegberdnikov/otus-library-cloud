package ru.otus.homework.otuslibraryui.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.otus.homework.otuslibraryui.dto.BookDto;

public interface BookService {
  Page<BookDto> getPaginatedListBooks(Pageable pageable);
  BookDto findById(Long bookId);
  void deleteById(long bookId);
  BookDto save(BookDto bookDto);
  BookDto update(BookDto bookDto);
}
