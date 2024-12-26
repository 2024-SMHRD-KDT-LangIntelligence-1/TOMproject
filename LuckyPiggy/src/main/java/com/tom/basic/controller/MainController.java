package com.tom.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tom.basic.entity.TbUser;
import com.tom.basic.model.UserVO;
import com.tom.basic.repository.UserRepo;

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
	
	
	
	
	@Autowired
	UserRepo repo;
	
	
	// 회원가입 기능
	@PostMapping("/join.do")
	public String join(UserVO vo) {
		
		TbUser en = new TbUser(vo);
		
		repo.save(en);
		
		return "redirect:/";
	}
	
	
	
	
}
