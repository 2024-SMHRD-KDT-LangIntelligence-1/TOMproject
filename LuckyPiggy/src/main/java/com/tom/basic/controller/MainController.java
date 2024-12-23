package com.tom.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	
	@GetMapping("/")
	public String home() {
		return "index.html";
	}
	
	@GetMapping("/daily")
	public String daily() {
		return "daily.html";
	}
	
	@GetMapping("/join")
	public String join() {
		return "join.html";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login.html";
	}
	
	@GetMapping("/startPage")
	public String startPage() {
		return "start_page.html";
	}
	
}
