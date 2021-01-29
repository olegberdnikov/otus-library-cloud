package ru.otus.homework.otuslibraryui.exception;

public class BookNotFoundException extends OtusLibraryException {

  public BookNotFoundException(Long id) {
    super("Not found book by id " +id);
  }

}
