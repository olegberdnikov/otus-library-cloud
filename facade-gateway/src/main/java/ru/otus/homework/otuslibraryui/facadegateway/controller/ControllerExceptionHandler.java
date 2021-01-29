package ru.otus.homework.otuslibraryui.facadegateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler extends
    org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler {

  @ExceptionHandler
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public String handleException(Exception exception, Model model) {
    model.addAttribute("errorMessage", exception.getMessage());
    return "error";
  }

}
