package com.multi.chap00_miniproject.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String main(){

        return "main/main";
    }

    @PostMapping("/")
    public String redirectMain(){

        return "redirect:/";
    }
}
