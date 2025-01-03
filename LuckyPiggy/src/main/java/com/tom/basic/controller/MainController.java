package com.tom.basic.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tom.basic.entity.TbAccount;
import com.tom.basic.entity.TbBudget;
import com.tom.basic.entity.TbCreditcard;
import com.tom.basic.entity.TbMoneybook;
import com.tom.basic.entity.TbUser;
import com.tom.basic.model.AccountVO;
import com.tom.basic.model.BudgetVO;
import com.tom.basic.model.CardsumVO;
import com.tom.basic.model.CreditcardVO;
import com.tom.basic.model.MoneybookVO;
import com.tom.basic.model.UserVO;
import com.tom.basic.model.postVO;
import com.tom.basic.repository.AccountRepo;
import com.tom.basic.repository.BudgetRepo;
import com.tom.basic.repository.CalenderRepo;
import com.tom.basic.repository.CardsumRepo;
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
   CalenderRepo calrepo;
   @Autowired
   BudgetRepo brepo;
   @Autowired
   CardsumRepo cardsumRepo;

   private final MoneybookRepo moneybookRepository;
   @Autowired
   public MainController(MoneybookRepo moneybookRepository) {
      this.moneybookRepository = moneybookRepository;
   }

   @GetMapping("/benefit")
   public String benefit(HttpServletRequest request, Model model) {
      HttpSession session = request.getSession();
      TbUser uid = (TbUser) session.getAttribute("user");

      if (uid == null) {
         return "redirect:/login"; // 로그인 페이지로 리다이렉트
      }

      String userid = uid.getUserId();
      List<CardsumVO> cardUsageData = cardsumRepo.findGroupBYReportWithNativeQuery1(userid);

      if (!cardUsageData.isEmpty()) {
         model.addAttribute("cardsum", cardUsageData.get(0)); // 첫 번째 데이터 전달
      }

      return "benefit";
   }

   @GetMapping("/")
   public String home() {
      return "start_page";
   }

   @GetMapping("/index")
   public String index() {
      return "index";
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
   public String calendar(HttpServletRequest request, Model model, @RequestParam("month") String month) {

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

//      List<TbMoneybook> moneybook_list = moneybook_repo.findAllByUserId(userid);
//      model.addAttribute("moneybook_list", moneybook_list);

      List<String> mb_type_list = moneybook_repo.findDistinctMbTypeByUserId(userid);
      model.addAttribute("mb_type_list", mb_type_list);

      // 달 입출금 표시
      System.out.println("내가보낸 달" + month);
      List<TbMoneybook> calist = calrepo.findEntriesInDecember2024(month, userid);
      System.out.println(calist);
      model.addAttribute("calist", calist);

      return "calendar";
   }

   @GetMapping("/daily")
   public String daily(HttpServletRequest request, Model model,
                       @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
       HttpSession session = request.getSession();
       TbUser uid = (TbUser) session.getAttribute("user");

       if (uid == null) {
           // 세션에서 user 정보를 찾을 수 없다면 로그인 페이지로 리다이렉트
           return "redirect:/login";
       }

       String userId = uid.getUserId();
       session.setAttribute("userid", userId);

       // 해당 날짜의 가계부 데이터 가져오기
       List<TbMoneybook> allRecords = moneybookRepository.findByPaidAtAndUserId(date, userId);
       if (allRecords == null) {
           allRecords = new ArrayList<>(); // null이면 빈 리스트로 초기화
       }

       // 수입 데이터 필터링 및 합계 계산
       List<TbMoneybook> incomeList = allRecords.stream()
           .filter(entry -> entry.getMbIc() != null && !entry.getMbIc().isEmpty()) // 수입만 필터링
           .toList();

       int totalIncome = incomeList.stream()
           .mapToInt(entry -> parseAmount(entry.getMbIc())) // String을 int로 변환
           .sum();

       // 지출 데이터 필터링 및 합계 계산
       List<TbMoneybook> expenseList = allRecords.stream()
           .filter(entry -> entry.getMbAmount() != null && !entry.getMbAmount().isEmpty()) // 지출만 필터링
           .toList();

       int totalExpense = expenseList.stream()
           .mapToInt(entry -> parseAmount(entry.getMbAmount())) // String을 int로 변환
           .sum();

       // Model에 데이터 추가
       model.addAttribute("incomeList", incomeList); // 수입 데이터
       model.addAttribute("expenseList", expenseList); // 지출 데이터
       model.addAttribute("totalIncome", totalIncome); // 총 수입
       model.addAttribute("totalExpense", totalExpense); // 총 지출

       // 카드 리스트 데이터 추가
       List<TbCreditcard> debitCardList = creditcard_repo.findAllByUserIdAndCardType(userId, "체크");
       model.addAttribute("debit_cardlist", debitCardList);

       List<TbCreditcard> creditCardList = creditcard_repo.findAllByUserIdAndCardType(userId, "신용");
       model.addAttribute("credit_cardlist", creditCardList);

       // 사용 내역 데이터 추가
       List<String> mbTypeList = moneybook_repo.findDistinctMbTypeByUserId(userId);
       model.addAttribute("mb_type_list", mbTypeList);

       return "daily";
   }

   // String을 안전하게 int로 변환하는 메서드
   private int parseAmount(String amount) {
       try {
           // 쉼표 제거 후 변환
           return Integer.parseInt(amount.replace(",", "").trim());
       } catch (NumberFormatException e) {
           return 0; // 숫자로 변환 불가능한 경우 기본값 0 반환
       }
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

   @GetMapping("/recommend")
   public String recommend() {
      return "recommend";
   }

   @GetMapping("/account")
   public String account() {
      return "account";
   }


   // 회원가입 기능
   @PostMapping("/join.do")
   public String join(UserVO userVO, CreditcardVO creditcardVO, AccountVO accountVO, BudgetVO budgetVO) {
      // 회원가입정보저장
      TbUser en = new TbUser(userVO);
      userRepo.save(en);
      
      // 카드정보저장
      TbCreditcard cen = new TbCreditcard(creditcardVO);
      creditcard_repo.save(cen);

      // 계좌정보저장
      TbAccount aen = new TbAccount(accountVO);
      account_repo.save(aen);

      // 예산정보저장
      TbBudget ben = new TbBudget(budgetVO);
      System.out.println(ben.getBudgetIdx());
      System.out.println(ben.getBudgetBalance());
      brepo.save(ben);


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

      return "redirect:/account";
   }

   @GetMapping("/main")
   public String graph(Model model, HttpServletRequest request) {
      HttpSession session = request.getSession();
      TbUser uid = (TbUser) session.getAttribute("user");

      String userid = uid.getUserId();
      session.setAttribute("userid", userid);
      System.out.println("저장된 유저아이디 가져오기" + userid);

      List<postVO> graphlist = graphRepo.findGroupBYReportWithNativeQuery(userid);
      model.addAttribute("eat", graphlist);

      List<TbMoneybook> moneybook_list7 = moneybook_repo.findAllByUserId7(userid);
      model.addAttribute("moneybook_list7", moneybook_list7);
      System.out.println(moneybook_list7);


      List<CardsumVO> cardsum = cardsumRepo.findGroupBYReportWithNativeQuery1(userid);
      model.addAttribute("cardsum", cardsum);
      if (cardsum == null) {
         cardsum = new ArrayList<>(); // 빈 리스트로 초기화
      }


      return "main";
   }

   // 가계부 등록 기능
   @PostMapping("/moneybook.do")
   public String moneybook(MoneybookVO vo) {

      TbMoneybook en = new TbMoneybook(vo);
      moneybook_repo.save(en);
      
        Date date = vo.getPaid_at();    
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
                
          String redirectUrl = "/calendar?month=" + month;
      
      return "redirect:" + redirectUrl;
   }
   
   @PostMapping("/moneybook.do.daily")
   public String moneybookdaily(MoneybookVO vo) {



      TbMoneybook en = new TbMoneybook(vo);
      moneybook_repo.save(en);
      
      String redirectUrl = "/daily?date=" + vo.getPaid_at();
      
      return "redirect:" + redirectUrl;
   }
   

   // 검색 기능
   @GetMapping("/search")
   public String search(HttpServletRequest request, Model model, @RequestParam("searchValue") String keyword) {
      HttpSession session = request.getSession();
      TbUser uid = (TbUser) session.getAttribute("user");
      String userid = uid.getUserId();

      List<TbMoneybook> searchlist = srepo.searchquery(keyword, userid);
      System.out.println(searchlist);

      model.addAttribute("searchlist", searchlist);

      return "search";
   }
   
   @PostMapping("/dmoneybook.do")
   public String dmoneybook(MoneybookVO vo) {

      TbMoneybook en = new TbMoneybook(vo);
      moneybook_repo.save(en);

      return "redirect:/daily";
   }

}
