package ru.otus.homework.otuslibraryui.exception;

public class AuthorNotFoundException extends OtusLibraryException {

  public AuthorNotFoundException(Long id) {
    super("Not found author by id " +id);
  }

}
