package ru.otus.homework.otuslibraryui.exception;

import java.io.IOException;

public class ConverterException extends OtusLibraryException {
  public ConverterException(String s, IOException e) {
    super("error convert  from value: " +s,e);
  }
}
