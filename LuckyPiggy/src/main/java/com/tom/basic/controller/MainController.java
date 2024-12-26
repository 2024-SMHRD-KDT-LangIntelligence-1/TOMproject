package com.tom.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/daily")
	public String daily() {
		return "daily";
	}
	
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/startPage")
	public String startPage() {
		return "start_page";
	}
	
	@GetMapping("/topbar")
	public String topbar() {
		return "top_bar";
	}
	
	@GetMapping("/calendar")
	public String calendar() {
		return "calendar";
	}
	
	@GetMapping("/mypage")
	public String mypage() {
		return "mypage";
	}
	
	@GetMapping("/card")
	public String card() {
		return "card";
	}
	
	@GetMapping("/account")
	public String account() {
		return "account";
	}
	
}
