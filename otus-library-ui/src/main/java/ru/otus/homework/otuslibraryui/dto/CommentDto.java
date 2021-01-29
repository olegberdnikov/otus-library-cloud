package ru.otus.homework.otuslibraryui.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CommentDto {

  @JsonProperty("CommentId")
  private Long commentId;

  @JsonProperty("Text")
  private String text;

  @JsonProperty("Created")
  private LocalDateTime created;

  @JsonProperty("BookId")
  private Long bookId;

}
