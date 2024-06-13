package com.multi.chap03_security.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class CustomErrorController {

    @RequestMapping("/denied")
    public  void accessDenied(){}

    @PostMapping("/login")
    public void loginFailed(){}


}
