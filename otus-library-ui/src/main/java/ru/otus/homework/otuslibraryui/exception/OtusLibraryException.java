package ru.otus.homework.otuslibraryui.exception;

public class OtusLibraryException extends RuntimeException {

  public OtusLibraryException() {}

  public OtusLibraryException(String message) {
    super(message);
  }
  public OtusLibraryException(String s, Throwable throwable) {
    super(s, throwable);
  }
}
