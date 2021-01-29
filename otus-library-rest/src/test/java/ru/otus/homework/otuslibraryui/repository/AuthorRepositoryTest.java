package ru.otus.homework.otuslibraryui.repository;

import static org.assertj.core.api.Assertions.assertThat;

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

@DisplayName("Repository для работы с авторами")
@DataJpaTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
class AuthorRepositoryTest {

  public static final int COUNT_AUTHOR = 4;
  public static final String AUTHOR_NAME = "Name";
  @Autowired private AuthorRepository authorRepository;
  @Autowired private TestEntityManager em;

  @Test
  void count() {
    final long count = authorRepository.count();
    assertThat(count).isEqualTo(COUNT_AUTHOR);
  }

  @Test
  void save() {
    final Author author =
        Author.builder().name("Пурышева").name2("Наталья").surname("Сергеевная").build();
    final Author savedAuthor = authorRepository.save(author);
    assertThat(em.find(Author.class, savedAuthor.getAuthorId())).isNotNull().isEqualTo(savedAuthor);
  }

  @Test
  void findById() {
    final Optional<Author> author = authorRepository.findById(1L);
    assertThat(author).isNotEmpty().matches(author1 -> author1.get().getAuthorId().equals(1L));
  }

  @Test
  void findAll() {
    final List<Author> allAuthors = authorRepository.findAll();
    assertThat(allAuthors).isNotEmpty();
  }

  @Test
  void deleteById() {
    authorRepository.deleteById(4L);
    assertThat(em.find(Author.class, 4L)).isNull();
  }

  @Test
  void update() {
    final Author author = em.find(Author.class, 1L);
    author.setName(AUTHOR_NAME);
    final Author updatedAuthor = authorRepository.save(author);
    assertThat(author).isEqualTo(updatedAuthor);
  }

}
