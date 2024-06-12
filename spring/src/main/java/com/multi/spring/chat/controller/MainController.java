package com.multi.spring.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
public class MainController {


	
	@RequestMapping("/main")
	public String main() {
		return "redirect:/index.jsp";
	}
	
	
	@RequestMapping("/chat/myShopChatBot")
	public void memberMain() {
	
	}
	
	
	@RequestMapping("/chat/realChat")
	public void insertForm() {
	
	}
}