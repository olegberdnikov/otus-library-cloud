package ru.otus.homework.otuslibraryui.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.homework.otuslibraryui.dto.AuthorDto;
import ru.otus.homework.otuslibraryui.dto.CategoryDto;
import ru.otus.homework.otuslibraryui.feign.RestService;

@Controller
@RequiredArgsConstructor
public class BookPageController {

  private final Logger logger = LoggerFactory.getLogger(BookPageController.class);

  private final RestService restService;

  @GetMapping("/book/booklist")
  public String listBook(Model model) {
    return "book/booklist";
  }

  @GetMapping("/book/view/{bookId}")
  public String viewBook(ModelMap model, @PathVariable Long bookId) {
    model.put("bookId", bookId);
    return "book/bookview";
  }

  @GetMapping("/book/delete/{bookId}")
  public String deleteBook(ModelMap model, @PathVariable Long bookId) {
    model.put("bookId", bookId);
    model.addAttribute("allowDelete", true);
    return "book/bookview";
  }

  @GetMapping("/book/addnew")
  public String addBook(ModelMap model) {
    final List<CategoryDto> categoryListDto = restService.getCategories();
    final List<AuthorDto> authorListDto = restService.getAuthors();
    model.addAttribute("add", true);
    model.addAttribute("categories", categoryListDto);
    model.addAttribute("allAuthors", authorListDto);
    return "book/bookedit";
  }

  @GetMapping("/book/edit/{bookId}")
  public String editBook(ModelMap model, @PathVariable Long bookId) {
    model.put("bookId", bookId);
    final List<CategoryDto> categoryListDto = restService.getCategories();
    final List<AuthorDto> authorListDto = restService.getAuthors();
    model.addAttribute("add", false);
    model.addAttribute("categories", categoryListDto);
    model.addAttribute("allAuthors", authorListDto);
    return "book/bookedit";
  }

}
