package ru.otus.homework.otuslibraryui.facadegateway.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ref_user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User implements Serializable {

  @JsonProperty("UserId")
  @Id
  @SequenceGenerator(name="ref_user_seq_gen",sequenceName="ref_user_seq",
      allocationSize=1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ref_user_seq_gen")
  @Column(name = "user_id", nullable = false)
  private Long userId;

  @JsonProperty("Name")
  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @JsonProperty("Password")
  @Column(name = "password", nullable = false)
  private String password;

  @JsonProperty("Authorities")
  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_name",referencedColumnName = "name")
  private List<Authority> authorities;

  public User(String name, String password) {
    this.name = name;
    this.password = password;
  }

}
