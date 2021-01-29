package ru.otus.homework.otuslibraryui.util.endpoint;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;
import ru.otus.homework.otuslibraryui.repository.BookRepository;

@Component
@Endpoint(id = "countentity")
public class CountEndpoint {

  private Map<String, String>
      contributors = new LinkedHashMap<>();

  @Autowired
  BookRepository bookRepository;

  @ReadOperation
  public Map<String, String> countBook() {
    contributors
        .put("Количество книг", String.valueOf(bookRepository.count()));
    return contributors;
  }

}
