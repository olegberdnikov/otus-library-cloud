package ru.otus.homework.otuslibraryui.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotEmpty;
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
public class BookDto {

  @JsonProperty("BookId")
  private Long bookId;

  @NotEmpty(message="Пустое наименование книги")
  @JsonProperty("Name")
  private String name;

  @NotEmpty(message="Пустой список авторов")
  @JsonProperty("Authors")
  private Set<AuthorDto> authors;

  @NotNull
  @JsonProperty("Category")
  private CategoryDto category;

  @JsonProperty("Comments")
  private List<CommentDto> comments;

}
