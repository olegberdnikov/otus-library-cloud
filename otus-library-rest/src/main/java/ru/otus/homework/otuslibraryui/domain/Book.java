package ru.otus.homework.otuslibraryui.domain;

import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Data
@Entity
@Table(name = "book")
@NamedEntityGraph(name = "otus-book-entity-graph",
    attributeNodes = {
        @NamedAttributeNode("authors"),
        @NamedAttributeNode("category")
    })
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

  @Id
  @SequenceGenerator(name="book_seq_gen",sequenceName="book_seq",
      allocationSize=1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="book_seq_gen")
  @Column(name = "book_id", nullable = false)
  private Long bookId;

  @Column(name = "name", nullable = false)
  private String name;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JoinTable(name="book_author",
      joinColumns = @JoinColumn(name = "book_id", nullable = false),
      inverseJoinColumns = @JoinColumn(name = "author_id", nullable = false))
  private Set<Author> authors;

  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  @OneToMany(orphanRemoval = true,  mappedBy = "book", cascade = CascadeType.ALL, targetEntity = Comment.class)
  @BatchSize(size=10)
  @Fetch(FetchMode.SELECT)
  private List<Comment> comments;

}
