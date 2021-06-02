package com.example.controller;

import com.example.model.Person;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
public class IndexController {

  @GetMapping("/")
  public String index(Model model) {
    log.info("This is Index Controller");
    model.addAttribute("person", new Person(101, "ABC"));
    return "index";
  }
}
