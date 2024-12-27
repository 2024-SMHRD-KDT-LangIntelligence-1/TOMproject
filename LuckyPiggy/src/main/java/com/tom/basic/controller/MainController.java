package com.tom.basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tom.basic.entity.GraphEntity;
import com.tom.basic.entity.TbUser;
import com.tom.basic.model.GraphVO;
import com.tom.basic.model.UserVO;
import com.tom.basic.model.postVO;
import com.tom.basic.repository.GraphRepo;
import com.tom.basic.repository.UserRepo;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	GraphRepo graphRepo;
	
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

	@GetMapping("/graph")
	public String graph(Model model) {
		List<postVO> graphlist = graphRepo.findGroupBYReportWithNativeQuery();
		
		System.out.println("가져온 것은");
		model.addAttribute("eat",graphlist);
		
		return "graph";
	}
	
	
	// 회원가입 기능
	@PostMapping("/join.do")
	public String join(UserVO vo) {
		
		TbUser en = new TbUser(vo);
		
		userRepo.save(en);
		
		return "redirect:/";
	}
	
	// 로그인 기능
	@PostMapping("/login.do")
	public String login(String user_id, String user_pw, HttpSession session) {
				
		TbUser enti = userRepo.findByUserIdAndUserPw(user_id, user_pw);
		
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
