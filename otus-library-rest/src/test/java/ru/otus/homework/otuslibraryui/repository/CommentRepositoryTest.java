package ru.otus.homework.otuslibraryui.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
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
import ru.otus.homework.otuslibraryui.domain.Book;
import ru.otus.homework.otuslibraryui.domain.Comment;

@DisplayName("Repository для работы с комментариями")
@DataJpaTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
class CommentRepositoryTest {

  public static final int COUNT_COMMENT = 4;
  public static final String COMMENT_TEXT = "CommentText";

  @Autowired private CommentRepository commentRepository;
  @Autowired private TestEntityManager em;

  @Test
  void count() {
    final long count = commentRepository.count();
    assertThat(count).isEqualTo(COUNT_COMMENT);
  }

  @Test
  void save() {
    Comment comment = new Comment();
    final Book book = new Book();
    book.setBookId(1L);
    comment.setBook(book);
    comment.setText("Text comment");
    comment.setCreated(LocalDateTime.now());
    final Comment savedComment = commentRepository.save(comment);
    assertThat(em.find(Comment.class, savedComment.getCommentId()))
        .isNotNull()
        .isEqualTo(savedComment);
  }

  @Test
  void findById() {
    final Optional<Comment> comment = commentRepository.findById(1L);
    assertThat(comment).isNotEmpty().matches(comment1 -> comment1.get().getBook().getBookId().equals(1L));
  }

  @Test
  void findAll() {
    final List<Comment> allComments = commentRepository.findAll();
    assertThat(allComments).isNotEmpty();
  }

  @Test
  void deleteById() {
    commentRepository.deleteById(1L);
    assertThat(em.find(Comment.class, 1L)).isNull();
  }

  @Test
  void update() {
    final Comment comment = em.find(Comment.class, 1L);
    comment.setText(COMMENT_TEXT);
    final Comment updatedComment = commentRepository.save(comment);
    assertThat(comment).isEqualTo(updatedComment);
  }

}
