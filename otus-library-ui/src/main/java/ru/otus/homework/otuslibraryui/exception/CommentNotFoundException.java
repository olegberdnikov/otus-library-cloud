package ru.otus.homework.otuslibraryui.exception;

public class CommentNotFoundException extends OtusLibraryException {

  public CommentNotFoundException(Long id) {
    super("Not found comment by id " +id);
  }

}
