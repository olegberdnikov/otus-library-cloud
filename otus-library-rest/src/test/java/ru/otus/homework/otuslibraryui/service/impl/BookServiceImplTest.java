package ru.otus.homework.otuslibraryui.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.homework.otuslibraryui.config.MapperConfig;
import ru.otus.homework.otuslibraryui.domain.Author;
import ru.otus.homework.otuslibraryui.domain.Book;
import ru.otus.homework.otuslibraryui.domain.Category;
import ru.otus.homework.otuslibraryui.exception.BookNotFoundException;
import ru.otus.homework.otuslibraryui.repository.BookRepository;
import ru.otus.homework.otuslibraryui.service.BookService;
import ru.otus.homework.otuslibraryui.util.ServiceMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@DisplayName("Сервисный слой для работы с книгами")
@ExtendWith(SpringExtension.class)
@Import({BookServiceImpl.class})
@ContextConfiguration(classes = MapperConfig.class)
class BookServiceImplTest {

  public static final long BOOK_ID = 3L;
  @MockBean private BookRepository bookRepository;
  @Autowired private ServiceMapper serviceMapper;
  @Autowired BookService bookService;

  private Book book;
  private Pageable pageable;

  @BeforeEach
  public void setUp() throws Exception {
    final Author author =
        Author.builder()
            .authorId(BOOK_ID)
            .name("Пурышева")
            .name2("Наталья")
            .surname("Сергеевная")
            .build();
    final Category category = new Category("Учебная литература");
    category.setCategoryId(2L);
    Set<Author> set = new HashSet<>();
    set.add(author);
    book =
        Book.builder()
            .authors(set)
            .category(category)
            .name("Учебник по физике")
            .bookId(BOOK_ID)
            .build();

    given(bookRepository.save(isA(Book.class))).willReturn(book);
    given(bookRepository.findById(isA(Long.class))).willReturn(Optional.of(book));
    pageable = PageRequest.of(1, 1);
    Page<Book> page = new PageImpl<>(List.of(book), pageable, 1);
    given(bookRepository.findAll(isA(Pageable.class))).willReturn(page);
    doNothing().when(bookRepository).deleteById(isA(Long.class));
  }

  @Test
  void getPaginatedListBooks() {
    assertThat(bookService.getPaginatedListBooks(pageable)).isNotEmpty();
  }

  @Test
  void findById() {
    assertThat(bookService.findById(BOOK_ID)).isEqualTo(serviceMapper.domainToBookDto(book));
  }

  @Test
  void notFoundById() {
    given(bookRepository.findById(isA(Long.class))).willReturn(Optional.empty());
    Throwable thrown =
        assertThrows(
            BookNotFoundException.class,
            () -> {
              assertThat(bookService.findById(BOOK_ID));
            });
    assertNotNull(thrown.getMessage());
  }

  @Test
  void deleteById() {
    bookService.deleteById(BOOK_ID);
  }

  @Test
  void save() {
    assertThat(bookService.save(serviceMapper.domainToBookDto(book)))
        .isEqualTo(serviceMapper.domainToBookDto(book));
  }

  @Test
  void update() {
    assertThat(bookService.update(serviceMapper.domainToBookDto(book)))
        .isEqualTo(serviceMapper.domainToBookDto(book));
  }

}
