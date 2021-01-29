package ru.otus.homework.otuslibraryui.facadegateway.controller;

import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HttpErrorController implements ErrorController {

  @RequestMapping("/error")
  public String handleError(Locale locale, Model model, HttpServletRequest request, Exception ex) {
    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    model.addAttribute("errorMessage", ex.getMessage());
    if (status != null) {
      int statusCode = Integer.parseInt(status.toString());
      // 403
      if (statusCode == HttpStatus.FORBIDDEN.value()) {
        return "/error/security-error";
      }
    }
    return "/error/error";
  }

  @Override
  public String getErrorPath() {
    return "/error";
  }

}
