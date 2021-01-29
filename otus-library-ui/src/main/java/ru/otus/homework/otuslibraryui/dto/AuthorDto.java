package ru.otus.homework.otuslibraryui.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AuthorDto {

  @JsonProperty("AuthorId")
  private Long authorId;

  @JsonProperty("Name")
  private String name;

  @JsonProperty("Surname")
  private String surname;

  @JsonProperty("Name2")
  private String name2;

  public AuthorDto(Long authorId) {
    this.authorId = authorId;
  }

}
