package ru.otus.homework.otuslibraryui.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.homework.otuslibraryui.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

  Optional<Book> findByName(String name);

  @EntityGraph(value = "otus-book-entity-graph", type = EntityGraphType.LOAD)
  List<Book> findAllByAuthorsAuthorId(Long authorId);

}
