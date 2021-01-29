package ru.otus.homework.otuslibraryui.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homework.otuslibraryui.dto.AuthorDto;
import ru.otus.homework.otuslibraryui.dto.BookDto;
import ru.otus.homework.otuslibraryui.exception.OtusLibraryException;
import ru.otus.homework.otuslibraryui.service.AuthorService;
import ru.otus.homework.otuslibraryui.service.BookService;
import ru.otus.homework.otuslibraryui.service.CategoryService;

@RestController
@RequiredArgsConstructor
public class BookRestController {

  private final Logger logger = LoggerFactory.getLogger(BookRestController.class);

  private final BookService bookService;
  private final CategoryService categoryService;
  private final AuthorService authorService;

  @GetMapping(value = "/authors/")
  public ResponseEntity<?> listBooks() {
    return ResponseEntity.ok(authorService.getAll());
  }

  @GetMapping(value = "/categories/")
  public ResponseEntity<?> listCategories() {
    return ResponseEntity.ok(categoryService.getAll());
  }

  @GetMapping(value = "/books/")
  public ResponseEntity<?> listBooks(
      @RequestParam(name = "page", defaultValue = "1") Integer page,
      @RequestParam(name = "size", defaultValue = "2") Integer size) {
    final PageRequest pageRequest = PageRequest.of(page - 1, size);
    final Page<BookDto> bookDtoPage = bookService.getPaginatedListBooks(pageRequest);
    return ResponseEntity.ok(bookDtoPage);
  }

  @GetMapping(value = "/book/{bookId}")
  public ResponseEntity<?> getBook(@PathVariable(name = "bookId") Long bookId) {
    return ResponseEntity.ok(bookService.findById(bookId));
  }

  @DeleteMapping(value = "/book/{bookId}")
  public ResponseEntity<?> deleteBook(@PathVariable(name = "bookId") Long bookId) {
    bookService.deleteById(bookId);
    return ResponseEntity.ok().build();
  }

  @PostMapping(value = "/book")
  public ResponseEntity<?> addBook(@Valid @RequestBody BookDto bookDto) {
    fillBookDtoAuthors(bookDto);
    BookDto newBook = bookService.save(bookDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(newBook.getBookId());
  }

  @PutMapping(value = "/book")
  public ResponseEntity<?> editBook(@Valid @RequestBody BookDto bookDto) {

    fillBookDtoAuthors(bookDto);
    BookDto newBook = bookService.update(bookDto);
    return ResponseEntity.ok(newBook.getBookId());
  }

  private void fillBookDtoAuthors(BookDto bookDto) {
    final List<Long> authorsIds =
        Optional.ofNullable(bookDto.getAuthors())
            .orElseThrow(() -> new OtusLibraryException("Пустой список авторов")).stream()
            .map(AuthorDto::getAuthorId)
            .collect(Collectors.toList());
    final List<AuthorDto> allByIds = authorService.getAllByIds(authorsIds);
    if (authorsIds.isEmpty()
        || !authorsIds.containsAll(
        allByIds.stream().map(AuthorDto::getAuthorId).collect(Collectors.toList()))) {
      throw new OtusLibraryException("Некорректный список авторов");
    }
    final Set<AuthorDto> authorDtos = Set.of(allByIds.toArray(new AuthorDto[]{}));
    bookDto.setAuthors(authorDtos);
  }

}
