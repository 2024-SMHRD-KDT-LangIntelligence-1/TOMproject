package com.tom.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tom.basic.entity.GraphEntity;
import com.tom.basic.repository.GraphRepo;

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

	@GetMapping("/graph")
	public String graph(Model model) {
		GraphEntity turtle = repo.findPostureByMbIdAndPosType("adkim", "거북목");

		model.addAttribute("turtle", turtle);
		System.out.println("가져온 값은" + turtle.getPosCount() + turtle.getMbId());
		return "graph";
	}

	@Autowired
	GraphRepo repo;

//	@GetMapping("/graph.do")
//	public String graphDo(Model model) {
//		GraphEntity turtle = repo.findPostureByMbIdAndPosType("adkim", "거북목");
//
//		model.addAttribute("turtle", turtle);
//		System.out.println("가져온 값은" + turtle.getPosCount() + turtle.getMbId());
//
//		return "redirect:/";
//	}

}
