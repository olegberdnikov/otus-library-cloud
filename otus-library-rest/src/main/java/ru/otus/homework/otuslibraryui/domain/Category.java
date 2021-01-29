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
@Table(name = "category")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

  @Id
  @SequenceGenerator(name="category_seq_gen",sequenceName="category_seq",
      allocationSize=1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="category_seq_gen")
  @Column(name = "category_id", nullable = false)
  private Long categoryId;

  @Column(name = "name", nullable = false)
  private String name;

  public Category(String name) {
    this.name = name;
  }

}
