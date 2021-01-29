package ru.otus.homework.otuslibraryui.util;

public interface MessageBundleResource {
  String getMessage(String messageCode);
  String getMessage(String messageCode, Object... params);
}
