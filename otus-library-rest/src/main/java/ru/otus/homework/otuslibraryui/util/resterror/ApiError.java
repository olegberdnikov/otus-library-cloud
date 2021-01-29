package ru.otus.homework.otuslibraryui.util.resterror;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiError {

  public static ResponseEntity<?> getResponseEntity(HttpStatus status) {
    return ResponseEntity.status(status).body(new ApiError(status));
  }

  public static ResponseEntity<?> getResponseEntity(HttpStatus status, Throwable ex) {
    return ResponseEntity.status(status).body(new ApiError(status, ex));
  }

  public static ResponseEntity<?> getResponseEntity(
      HttpStatus status, String message, Throwable ex) {
    return ResponseEntity.status(status).body(new ApiError(status, message, ex));
  }

  public static ResponseEntity<?> getResponseEntity(
      HttpStatus status, String message) {
    return ResponseEntity.status(status).body(new ApiError(status, message));
  }
  @JsonProperty("status")
  private HttpStatus status;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private LocalDateTime timestamp;
  @JsonProperty("message")
  private String message;
  @JsonProperty("debugMessage")
  private String debugMessage;
  @JsonProperty("subErrors")
  private List<ApiSubError> subErrors;

  private ApiError() {
    timestamp = LocalDateTime.now();
  }

  private ApiError(HttpStatus status) {
    this();
    this.status = status;
  }

  private ApiError(HttpStatus status, Throwable ex) {
    this();
    this.status = status;
    this.message = "Unexpected error";
    this.debugMessage = ex.getLocalizedMessage();
  }

  private ApiError(HttpStatus status, String message, Throwable ex) {
    this();
    this.status = status;
    this.message = message;
    this.debugMessage = ex.getLocalizedMessage();
  }

  private ApiError(HttpStatus status, String message) {
    this();
    this.status = status;
    this.message = message;
  }

}
