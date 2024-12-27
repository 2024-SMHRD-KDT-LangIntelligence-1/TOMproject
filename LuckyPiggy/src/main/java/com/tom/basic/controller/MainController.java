package com.tom.basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tom.basic.entity.TbAccount;
import com.tom.basic.entity.TbBenefit;
import com.tom.basic.entity.TbCreditcard;
import com.tom.basic.entity.TbUser;
import com.tom.basic.model.AccountVO;
import com.tom.basic.model.BenefitVO;
import com.tom.basic.model.CreditcardVO;
import com.tom.basic.model.UserVO;
import com.tom.basic.repository.AccountRepo;
import com.tom.basic.repository.BenefitRepo;
import com.tom.basic.repository.CreditcardRepo;
import com.tom.basic.repository.UserRepo;

import jakarta.persistence.Tuple;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {


	
	@Autowired
	UserRepo user_repo;
	
	@Autowired
	CreditcardRepo creditcard_repo;
	
	@Autowired
	AccountRepo account_repo;
	
	
	
	
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
	public String card(HttpSession session) {
		
		TbUser enti = (TbUser)session.getAttribute("user");
		
		List<CreditcardVO> list = creditcard_repo.findAllByUserId(enti.getUserId());
		
		System.out.println("test");
		
		return "card";
	}
	
	@GetMapping("/account")
	public String account() {
		return "account";
	}
	

	
	
	// 회원가입 기능
	@PostMapping("/join.do")
	public String join(UserVO vo) {
		
		TbUser en = new TbUser(vo);
		
		user_repo.save(en);
		
		return "redirect:/";
	}
	
	// 로그인 기능
	@PostMapping("/login.do")
	public String login(String user_id, String user_pw, HttpSession session) {
				
		TbUser enti = user_repo.findByUserIdAndUserPw(user_id, user_pw);
		
		session.setAttribute("user", enti);
		
		
		return "redirect:/";
	}
	
	// 로그아웃 기능
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.removeAttribute("user");
		
		return "redirect:/";
	}
	
	// 마이페이지 비밀번호, 닉네임 변경 기능
	@PostMapping("/mypage.do")
	public String mypage(UserVO vo) {
		
		TbUser en = new TbUser(vo);
		
		user_repo.save(en);
		
		return "redirect:/";
	}
	
	// 카드 등록 기능
	@PostMapping("/card.do")
	public String card(CreditcardVO vo) {
		
		TbCreditcard en = new TbCreditcard(vo);
		creditcard_repo.save(en);
		
		return "redirect:/card";
		
	}
	
	// 계좌 등록 기능
	@PostMapping("/account.do")
	public String account(AccountVO vo) {
				
		TbAccount en = new TbAccount(vo);
		account_repo.save(en);
		
		return "redirect:/";
	}
	
	
	
}
