package ru.otus.homework.otuslibraryui.facadegateway.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class IndexController {

  @GetMapping("/login")
  public String login(Model model) {
    return "login";
  }

}
