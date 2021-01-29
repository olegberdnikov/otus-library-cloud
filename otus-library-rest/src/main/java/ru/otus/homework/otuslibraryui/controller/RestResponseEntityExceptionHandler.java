package ru.otus.homework.otuslibraryui.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.otus.homework.otuslibraryui.exception.BookNotFoundException;
import ru.otus.homework.otuslibraryui.exception.OtusLibraryException;
import ru.otus.homework.otuslibraryui.util.resterror.ApiError;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {BookNotFoundException.class})
  protected ResponseEntity<?> handleBookNotFoundException(RuntimeException ex, WebRequest request) {
    return ApiError.getResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage());
  }

  @ExceptionHandler(value = {OtusLibraryException.class})
  protected ResponseEntity<?> handleOtusLibraryException(RuntimeException ex, WebRequest request) {
    return ApiError.getResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY,ex.getMessage(),ex);
  }

  @ExceptionHandler(value = {Exception.class})
  protected ResponseEntity<?> handleException(RuntimeException ex, WebRequest request) {
    return ApiError.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR,"Error handle");
  }

}
