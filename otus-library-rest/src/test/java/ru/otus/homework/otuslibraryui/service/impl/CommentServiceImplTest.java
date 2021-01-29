package ru.otus.homework.otuslibraryui.service.impl;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.homework.otuslibraryui.config.MapperConfig;
import ru.otus.homework.otuslibraryui.domain.Comment;
import ru.otus.homework.otuslibraryui.repository.CommentRepository;
import ru.otus.homework.otuslibraryui.service.CommentService;

@DisplayName("Сервисный слой для работы с комментариями")
@ExtendWith(SpringExtension.class)
@Import(CommentServiceImpl.class)
@ContextConfiguration(classes = MapperConfig.class)
class CommentServiceImplTest {

  public static final long COMMENT_ID = 1L;
  @MockBean private CommentRepository commentRepository;

  @Autowired CommentService commentService;

  @BeforeEach
  public void setUp() throws Exception {
    final Comment comment = Comment.builder().text("comment").commentId(COMMENT_ID).build();
    given(commentRepository.findById(isA(Long.class))).willReturn(Optional.of(comment));
    doNothing().when(commentRepository).deleteById(isA(Long.class));
  }

  @Test
  void deleteById() {
    commentService.deleteById(COMMENT_ID);
  }

}
