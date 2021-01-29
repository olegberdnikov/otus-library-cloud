package ru.otus.homework.otuslibraryui.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.homework.otuslibraryui.domain.Author;
import ru.otus.homework.otuslibraryui.domain.Book;
import ru.otus.homework.otuslibraryui.domain.Category;
import ru.otus.homework.otuslibraryui.domain.Comment;

@DisplayName("Repository для работы с книгами")
@DataJpaTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
class BookRepositoryTest {

  public static final int COUNT_BOOK = 4;
  public static final String BOOK_NAME = "sdfdsf";
  public static final String NAME_BOOK = "Иуда освобожденный";
  @Autowired private BookRepository bookRepository;
  @Autowired private TestEntityManager em;

  @Test
  void count() {
    final long count = bookRepository.count();
    assertThat(count).isEqualTo(COUNT_BOOK);
  }

  @Test
  void save() {
    final Author author = Author.builder()
        .authorId(3L)
        .name("Пурышева")
        .name2("Наталья")
        .surname("Сергеевная")
        .build();
    final Category category = new Category("Учебная литература");
    category.setCategoryId(2L);
    Book book = Book.builder()
        .authors( Collections.singleton(author))
        .category(category)
        .name("Учебник по физике")
        .build();

    final Book savedBook = bookRepository.save(book);
    assertThat(em.find(Book.class, savedBook.getBookId())).isNotNull().isEqualTo(savedBook);
  }

  @Test
  void findById() {
    final Optional<Book> book = bookRepository.findById(1L);
    assertThat(book)
        .isNotEmpty()
        .matches(
            book1 -> book1.get().getBookId().equals(1L));
  }

  @Test
  void findAll() {
    final List<Book> allBooks = bookRepository.findAll();
    assertThat(allBooks).isNotEmpty();
  }

  @Test
  void deleteById() {
    final Optional<Book> byId = bookRepository.findById(3L);
    final Comment comment = byId.get().getComments().get(0);
    bookRepository.deleteById(3L);
    assertThat(em.find(Book.class,3L)).isNull();
    assertThat(em.find(Comment.class,comment.getCommentId())).isNull();
  }

  @Test
  void update() {
    final Book book = em.find(Book.class, 4L);
    book.setName(BOOK_NAME);
    book.getComments().add(new Comment("comment"));
    final Book updatedBook = bookRepository.save(book);
    assertThat(book).isEqualTo(updatedBook);
  }

  @Test
  void findAllByAuthors() {
    final List<Book> allByAuthors = bookRepository.findAllByAuthorsAuthorId(1L);
    assertThat(allByAuthors).isNotEmpty().hasSize(3);
  }

  @Test
  void findByName() {
    final Book book = em.find(Book.class, 4L);
    final Optional<Book> bookOptional = bookRepository.findByName(book.getName());
    assertThat(bookOptional).isNotEmpty().matches(p -> p.get().getName().equals(book.getName()));
  }
}
