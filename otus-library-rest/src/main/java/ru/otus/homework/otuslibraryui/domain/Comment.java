package ru.otus.homework.otuslibraryui.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {

  @Id
  @SequenceGenerator(name="comment_seq_gen",sequenceName="comment_seq",
      allocationSize=1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="comment_seq_gen")
  @Column(name = "comment_id")
  private Long commentId;

  @Column(name = "text")
  private String text;

  @Column(name = "created")
  private LocalDateTime created;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(
      name = "book_id",
      referencedColumnName = "book_id"
  )
  private Book book;

  public Comment(String text) {
    this.text = text;
  }

}
