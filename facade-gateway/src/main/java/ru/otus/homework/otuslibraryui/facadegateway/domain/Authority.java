package ru.otus.homework.otuslibraryui.facadegateway.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ref_authority",
    uniqueConstraints={@UniqueConstraint(columnNames={"user_name","authority"})})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Authority {

  @Id
  @SequenceGenerator(name="ref_authority_gen",sequenceName="ref_authority_seq",
      allocationSize=1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ref_authority_gen")
  @Column(name = "authority_id", nullable = false)
  private Long authorityId;

  @Column(name = "user_name", nullable = false)
  private String userName;

  @Column(name = "authority", nullable = false)
  private String authority;

}
