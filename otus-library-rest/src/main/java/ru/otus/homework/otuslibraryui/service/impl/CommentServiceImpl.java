package ru.otus.homework.otuslibraryui.service.impl;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.otuslibraryui.domain.Comment;
import ru.otus.homework.otuslibraryui.exception.CommentNotFoundException;
import ru.otus.homework.otuslibraryui.repository.CommentRepository;
import ru.otus.homework.otuslibraryui.service.CommentService;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;

  @Transactional
  @Override
  public void deleteById(long commentId) {
    final Optional<Comment> comment = commentRepository.findById(commentId);
    commentRepository.delete(comment.orElseThrow(() -> new CommentNotFoundException(commentId)));
  }
}
