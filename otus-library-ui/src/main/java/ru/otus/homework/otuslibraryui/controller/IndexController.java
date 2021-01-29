package ru.otus.homework.otuslibraryui.controller;

import java.util.Calendar;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.homework.otuslibraryui.configs.LocalizationConfig;
import ru.otus.homework.otuslibraryui.configs.YamlProperties;

@Controller
@AllArgsConstructor
@Import({LocalizationConfig.class, YamlProperties.class})
public class IndexController {

  @RequestMapping("/")
  public String home(Model model) {
    model.addAttribute("today", Calendar.getInstance());
    return "home";
  }

}
