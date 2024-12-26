package com.tom.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tom.basic.entity.TbUser;
import com.tom.basic.model.UserVO;
import com.tom.basic.repository.UserRepo;

import jakarta.servlet.http.HttpSession;

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
	

	
	@Autowired
	UserRepo repo;
	
	
	// 회원가입 기능
	@PostMapping("/join.do")
	public String join(UserVO vo) {
		
		TbUser en = new TbUser(vo);
		
		repo.save(en);
		
		return "redirect:/";
	}
	
	// 로그인 기능
	@PostMapping("/login.do")
	public String login(String user_id, String user_pw, HttpSession session) {
				
		TbUser enti = repo.findByUserIdAndUserPw(user_id, user_pw);
		
		session.setAttribute("user", enti);
		
		
		return "redirect:/";
	}
	
	// 로그아웃 기능
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.removeAttribute("user");
		
		return "redirect:/";
	}
	
	
	
}
