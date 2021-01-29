package ru.otus.homework.otuslibraryui.service.impl;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.otuslibraryui.domain.Book;
import ru.otus.homework.otuslibraryui.dto.BookDto;
import ru.otus.homework.otuslibraryui.exception.BookNotFoundException;
import ru.otus.homework.otuslibraryui.repository.BookRepository;
import ru.otus.homework.otuslibraryui.service.BookService;
import ru.otus.homework.otuslibraryui.util.ServiceMapper;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;
  private final ServiceMapper serviceMapper;

  @Override
  public Page<BookDto> getPaginatedListBooks(Pageable pageable) {
    final Page<Book> books = bookRepository.findAll(pageable);
    return books.map(serviceMapper::domainToBookDto);
  }

  @Override
  public BookDto findById(Long bookId) {
    return serviceMapper.domainToBookDto(
        bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId)));
  }

  @Transactional
  @Override
  public void deleteById(long bookId) {
    final Optional<Book> byId = bookRepository.findById(bookId);
    bookRepository.delete(byId.orElseThrow(() -> new BookNotFoundException(bookId)));
  }

  @Transactional
  @Override
  public BookDto save(BookDto bookDto) {
    final Book book = serviceMapper.dtoToBookDomain(bookDto);
    bookRepository.save(book);
    return serviceMapper.domainToBookDto(book);
  }

  @Transactional
  @Override
  public BookDto update(BookDto bookDto) {
    final Book book =
        bookRepository
            .findById(bookDto.getBookId())
            .orElseThrow(() -> new BookNotFoundException(bookDto.getBookId()));
    serviceMapper.updateBookFromDto(bookDto, book);
    return serviceMapper.domainToBookDto(bookRepository.save(book));
  }

}
