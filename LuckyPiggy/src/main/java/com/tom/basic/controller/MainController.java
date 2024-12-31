package com.tom.basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tom.basic.entity.TbAccount;
import com.tom.basic.entity.TbBudget;
import com.tom.basic.entity.TbCreditcard;
import com.tom.basic.entity.TbMoneybook;
import com.tom.basic.entity.TbUser;
import com.tom.basic.model.AccountVO;
import com.tom.basic.model.BudgetVO;
import com.tom.basic.model.CreditcardVO;
import com.tom.basic.model.MoneybookVO;
import com.tom.basic.model.UserVO;
import com.tom.basic.model.postVO;
import com.tom.basic.repository.AccountRepo;
import com.tom.basic.repository.BudgetRepo;
import com.tom.basic.repository.CreditcardRepo;
import com.tom.basic.repository.GraphRepo;
import com.tom.basic.repository.MoneybookRepo;
import com.tom.basic.repository.SearchRepo;
import com.tom.basic.repository.UserRepo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	@Autowired
	UserRepo userRepo;
	@Autowired
	CreditcardRepo creditcard_repo;
	@Autowired
	AccountRepo account_repo;
	@Autowired
	GraphRepo graphRepo;
	@Autowired
	MoneybookRepo moneybook_repo;
	@Autowired
	SearchRepo srepo;

    @Autowired
    BudgetRepo brepo ;


	@GetMapping("/index")
	public String index() {
		return "index";
	}

	@GetMapping("/")
	public String ho0me() {
		return "start_page";
	}

	@GetMapping("/startpage")
	public String startPage() {
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

	// 월요일 아침에 가계부 신용/체크 따라서 가져오는 db 다르게 설정하기 구현
	@GetMapping("/calendar")
	public String calendar(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		TbUser uid = (TbUser) session.getAttribute("user");

		String userid = uid.getUserId();
		session.setAttribute("userid", userid);
		System.out.println("카드리스트 유저 아이디는:" + userid);


		List<TbCreditcard> debit_cardlist = creditcard_repo.findAllByUserIdAndCardType(userid, "체크");
		model.addAttribute("debit_cardlist", debit_cardlist);

		// System.out.println(debit_cardlist);

		List<TbCreditcard> credit_cardlist = creditcard_repo.findAllByUserIdAndCardType(userid, "신용");
		model.addAttribute("credit_cardlist", credit_cardlist);

		// System.out.println(credit_cardlist);

//		List<TbMoneybook> moneybook_list = moneybook_repo.findAllByUserId(userid);
//		model.addAttribute("moneybook_list", moneybook_list);

		List<String> mb_type_list = moneybook_repo.findDistinctMbTypeByUserId(userid);
		model.addAttribute("mb_type_list", mb_type_list);

		return "calendar";
	}

	   @GetMapping("/daily")
	   public String daily(HttpServletRequest request, Model model) {
	      HttpSession session = request.getSession();
	      TbUser uid = (TbUser) session.getAttribute("user");

	      String userid = uid.getUserId();
	      session.setAttribute("userid", userid);
	      System.out.println("저장된 유저아이디 가져오기" + userid);
	      
	      TbBudget bud = brepo.findByUserId(userid);
	      model.addAttribute("budget", bud);
	      return "daily";
	   }

	@GetMapping("/mypage")
	public String mypage() {
		return "mypage";
	}

	@GetMapping("/card")
	public String card(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		TbUser uid = (TbUser) session.getAttribute("user");

		String userid = uid.getUserId();
		session.setAttribute("userid", userid);
		System.out.println("저장된 유저아이디 가져오기" + userid);

		List<TbCreditcard> cardlist = creditcard_repo.findAllByUserId(userid);
		model.addAttribute("cardlist", cardlist);

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

	@GetMapping("/main")
	public String graph(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		TbUser uid = (TbUser) session.getAttribute("user");

		String userid = uid.getUserId();
		session.setAttribute("userid", userid);
		System.out.println("저장된 유저아이디 가져오기" + userid);
		List<postVO> graphlist = graphRepo.findGroupBYReportWithNativeQuery(userid);

		System.out.println("가져온 것은");
		model.addAttribute("eat", graphlist);

		List<TbMoneybook> moneybook_list7 = moneybook_repo.findAllByUserId7(userid);
		model.addAttribute("moneybook_list7", moneybook_list7);
		
		System.out.println(moneybook_list7);
		
		return "main";
	}

	// 가계부 등록 기능
	@PostMapping("/moneybook.do")
	public String moneybook(MoneybookVO vo) {

		TbMoneybook en = new TbMoneybook(vo);
		moneybook_repo.save(en);

		return "redirect:/calendar";
	}

	// 검색 기능
	@RequestMapping("/search")
	public String search(HttpServletRequest request, Model model, @RequestParam("searchValue") String keyword) {
		HttpSession session = request.getSession();
		TbUser uid = (TbUser) session.getAttribute("user");
		String userid = uid.getUserId();

		List<TbMoneybook> searchlist = srepo.searchquery(keyword, userid);
		System.out.println(searchlist);

		model.addAttribute("searchlist", searchlist);

		return "search";
	}

	// 데일리



	@PostMapping("/dmoneybook.do")
	public String dmoneybook(MoneybookVO vo) {
		
		

		TbMoneybook en = new TbMoneybook(vo);
		moneybook_repo.save(en);

		return "redirect:/daily";
	}

}
