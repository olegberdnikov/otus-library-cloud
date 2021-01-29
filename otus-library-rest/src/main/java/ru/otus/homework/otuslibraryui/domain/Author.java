package ru.otus.homework.otuslibraryui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "author")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {

  @Id
  @SequenceGenerator(name="author_seq_gen",sequenceName="author_seq",
      allocationSize=1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="author_seq_gen")
  @Column(name = "author_id", nullable = false)
  private Long authorId;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "surname", nullable = false)
  private String surname;

  @Column(name = "name2", nullable = false)
  private String name2;

}
