package ru.otus.homework.otuslibraryui.exception;

public class CategoryNotFoundException extends OtusLibraryException {

  public CategoryNotFoundException(Long id) {
    super("Not found category by id " + id);
  }
}
