package ru.otus.homework.otuslibraryui.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.homework.otuslibraryui.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {}
