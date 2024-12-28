package com.tom.basic.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tom.basic.entity.TbAccount;
import com.tom.basic.entity.TbCreditcard;
import com.tom.basic.entity.TbUser;
import com.tom.basic.model.AccountVO;
import com.tom.basic.model.CreditcardVO;
import com.tom.basic.model.UserVO;
import com.tom.basic.model.postVO;
import com.tom.basic.repository.AccountRepo;
import com.tom.basic.repository.CreditcardRepo;
import com.tom.basic.repository.GraphRepo;
import com.tom.basic.repository.UserRepo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class MainController {
	@Autowired
	UserRepo userRepo;	
	@Autowired
	GraphRepo graphRepo;
	@Autowired
	CreditcardRepo creditcard_repo;
	@Autowired
	AccountRepo account_repo;
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/")
	public String home() {
		return "start_page";
	}

	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/topbar")
	public String topbar() {
		return "top_bar";
	}
	
	@GetMapping("/main")
	public String main() {
		return "main";
	}
	
	@GetMapping("/calendar")
	public String calendar() {
		return "calendar";
	}
	
	@GetMapping("/daily")
	public String daily() {
		return "daily";
	}

	@GetMapping("/mypage")
	public String mypage() {
		return "mypage";
	}

	@GetMapping("/card")
	public String card(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		TbUser uid = (TbUser)session.getAttribute("user");
		
		String userid = uid.getUserId();
		session.setAttribute("userid", userid);
		System.out.println("저장된 유저아이디 가져오기" + userid);
		
		List<TbCreditcard> cardlist = creditcard_repo.findAllByUserId(userid);
		model.addAttribute("cardlist",cardlist);
		
		
		
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
		
		return "redirect:/main";
	}
	
	// 로그아웃 기능
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.removeAttribute("user");
		
		return "start_page";
	}
	
	// 마이페이지 비밀번호, 닉네임 변경 기능
	@PostMapping("/mypage.do")
	public String mypage(UserVO vo) {
		
		TbUser en = new TbUser(vo);
		
		userRepo.save(en);
		
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
