// 변수로 저장
const currentDate = document.querySelector(".month-name"), // 월 이름을 표시할 요소
  dayTags = document.querySelector(".days"), // 날짜를 표시할 요소
  preNextBtn = document.querySelectorAll(".month-btn button"), // 이전/다음 버튼
  addExpenseButton = document.querySelector(".add-btn"), // 추가 버튼 정의
  cardSelect = document.querySelector("select[name='card-name']"),
  categorySelect = document.querySelector("select[name='category-name']");

// 현재 날짜를 가져옴
let date = new Date();
currYear = date.getFullYear(); // 현재 연도
currMonth = date.getMonth(); // 현재 월 (0~11)

// 월 이름 배열
const months = [
  "January",
  "February",
  "March",
  "April",
  "May",
  "June",
  "July",
  "August",
  "September",
  "October",
  "November",
  "December",
];

// 날짜 클릭 시 daily.html 페이지로 이동하는 함수
function redirectToDailyPage(year, month, day) {
  // 날짜를 YYYY-MM-DD 형식으로 변환
  const dateStr = `${year}-${String(month + 1).padStart(2, "0")}-${String(
    day
  ).padStart(2, "0")}`;
  // URL에 날짜를 쿼리 파라미터로 전달하여 daily.html로 이동
  window.location.href = `daily?date=${dateStr}`;
}

// 달력을 렌더링하는 함수
const renderCalendar = () => {
  // 해당 월의 첫 날 요일 (0: 일요일, 1: 월요일 등)
  let firstDayOfMonth = new Date(currYear, currMonth, 1).getDay(),
    // 해당 월의 마지막 날짜
    lastDateOfMonth = new Date(currYear, currMonth + 1, 0).getDate(),
    // 해당 월의 마지막 날 요일 (0~6)
    lastDayOfMonth = new Date(currYear, currMonth, lastDateOfMonth).getDay(),
    // 이전 달의 마지막 날짜
    lastDayOfLastMonth = new Date(currYear, currMonth, 0).getDate();

  let liTag = ""; // 날짜 목록을 저장할 변수

  // 이전 달의 날짜를 빈 공간으로 표시 (첫날 전에 해당하는 날짜들)
  for (let i = firstDayOfMonth; i > 0; i--) {
    liTag += `<li class="inactive">${lastDayOfLastMonth - i + 1}</li>`;
  }

  // 이번 달의 날짜를 표시
  for (let i = 1; i <= lastDateOfMonth; i++) {
    // 오늘 날짜를 'active' 클래스로 표시
    let isToday =
      i === date.getDate() &&
      currMonth === new Date().getMonth() &&
      currYear === new Date().getFullYear()
        ? "active"
        : "";
    liTag += `<li class="day ${isToday}" data-day="${i}">${i}</li>`; // 클릭할 수 있는 날짜에 data-day 속성 추가
  }

  // 다음 달의 날짜를 빈 공간으로 표시 (마지막 날 이후 해당하는 날짜들)
  for (let i = lastDayOfMonth; i < 6; i++) {
    liTag += `<li class="inactive">${i - lastDayOfMonth + 1}</li>`;
  }

  // 월 이름 업데이트
  currentDate.innerText = `${months[currMonth]}`;
  // 달력 내용 업데이트
  dayTags.innerHTML = liTag;

  // 날짜 클릭 이벤트트
  dayTags.addEventListener("click", (e) => {
    // 클릭된 요소가 .day 클래스인 경우
    if (e.target.classList.contains("day")) {
      const day = e.target.dataset.day; // 클릭된 날짜 가져오기
      redirectToDailyPage(currYear, currMonth, day); // 날짜 클릭 시 daily.html로 이동
    }
  });
};

// 월 렌더링
renderCalendar();

// 이전, 다음 버튼 클릭 시 월 변경
preNextBtn.forEach((btn) => {
  btn.addEventListener("click", () => {
    // 이전 버튼 클릭 시
    if (btn.id === "prev") {
      currMonth--; // 월을 하나 감소시킴
      if (currMonth < 0) {
        // 월이 0보다 작으면 12월로 이동하고 연도도 감소
        currMonth = 11;
        currYear--;
      }
    } else if (btn.id === "next") {
      // 다음 버튼 클릭 시
      currMonth++; // 월을 하나 증가시킴
      if (currMonth > 11) {
        // 월이 12보다 크면 1월로 이동하고 연도도 증가
        currMonth = 0;
        currYear++;
      }
    }

    // 새로운 Date 객체로 업데이트
    date = new Date(currYear, currMonth);
    renderCalendar(); // 월 변경 후 다시 달력 렌더링
  });
});

// 팝업 열기
function openPopup() {
  resetForm();
  document.getElementById("modal").style.display = "block";
  document.getElementById("cover").style.pointerEvents = 'none'; // 캘린더 클릭 비활성화
}

// 팝업 닫기
function closePopup() {
  resetForm();
  document.getElementById("modal").style.display = "none";
  document.getElementById("cover").style.pointerEvents = 'auto'; // 캘린더 클릭 활성화
}

// 버튼 클릭 시 'active' 클래스를 추가하고 나머지 버튼 비활성화
function handleButtonClick(selectedButton) {
  // 입금/출금 버튼만 비활성화 (결제 방식 버튼은 그대로)
  const statementButtons = document.querySelectorAll('.statements button'); // 입금/출금 버튼들
  statementButtons.forEach((button) => {
    button.classList.add('disabled'); // 비활성화 클래스 추가
    button.style.pointerEvents = 'none'; // 클릭할 수 없게 만듬
  });

  // 선택된 버튼만 활성화
  selectedButton.classList.remove('disabled');
  selectedButton.classList.add('active'); // 선택된 버튼에 active 클래스 추가
}

// 입금 버튼 클릭 이벤트
document.querySelector('.income-btn').addEventListener('click', function() {
  handleButtonClick(this); // 클릭된 버튼만 활성화
  showCashOnly(); // 입금 클릭 시 현금만 보이게
  document.querySelector('#money').setAttribute('name','mb_ic');
});

// 출금 버튼 클릭 이벤트
document.querySelector('.expense-btn').addEventListener('click', function() {
  handleButtonClick(this); // 클릭된 버튼만 활성화
  showAllPayments(); // 출금 클릭 시 모든 결제 방식 버튼 보이게
  document.querySelector('#money').setAttribute('name','mb_amount');
});

// 저장 버튼 클릭 시 초기화
function confirmSave() {
  const result = confirm("저장하시겠습니까?");
  
  if (result) {
    // 사용자가 "OK"를 클릭한 경우 저장 처리 로직
	
    alert("저장되었습니다.");

    // 내용 초기화
    // resetForm(); // 폼 초기화 함수 호출

    closePopup(); // 팝업 닫기
  } else {
    // 사용자가 "취소"를 클릭한 경우 팝업을 닫지 않고 모든 버튼 활성화
    resetForm(); // 버튼 활성화 및 초기화
  }
}

// 입력 필드와 버튼 초기화 함수
function resetForm() {
  // 금액과 메모 입력 필드 초기화
  document.getElementById("money").value = "";  // 금액 필드 초기화
  document.getElementById("memo").value = "";   // 메모 필드 초기화

  // 버튼 초기화 (active 클래스 제거)
  const allButtons = document.querySelectorAll('button');
  allButtons.forEach((button) => {
    button.classList.remove('active');  // 모든 버튼에서 active 클래스 제거
    button.classList.remove('disabled');  // 모든 버튼에서 disabled 클래스 제거
    button.style.pointerEvents = 'auto';  // 버튼을 다시 클릭할 수 있게 만듬

  // select 태그 초기화
  document.getElementById('method_nm').value="";
  document.getElementById('mb_type').value="";
  });

  // 결제 방식 버튼 초기화 (숨겨둔 버튼을 다시 보이게)
  document.querySelector('.debit-btn').classList.remove('hidden');
  document.querySelector('.credit-btn').classList.remove('hidden');
  document.querySelector('.cash-btn').classList.remove('hidden');
  
  // 입금/출금 상태 초기화
  document.querySelector('.income-btn').classList.remove('active');
  document.querySelector('.expense-btn').classList.remove('active');
}


// 입금 버튼 클릭 시 현금만 보이도록 하는 함수
function showCashOnly() {
  document.querySelector('.debit-btn').classList.add('hidden');
  document.querySelector('.credit-btn').classList.add('hidden');
  document.querySelector('.cash-btn').classList.remove('hidden');
}

// 출금 버튼 클릭 시 모든 결제 방식 버튼을 보이도록 하는 함수
function showAllPayments() {
  document.querySelector('.debit-btn').classList.remove('hidden');
  document.querySelector('.credit-btn').classList.remove('hidden');
  document.querySelector('.cash-btn').classList.remove('hidden');
}

// 결제 방식 버튼 클릭 시 다른 버튼 비활성화하는 함수
function handlePaymentButtonClick(selectedButton) {
  const paymentButtons = document.querySelectorAll('.payment button'); // 결제 방식 버튼들
  
  // 모든 결제 방식 버튼을 비활성화
  paymentButtons.forEach((button) => {
    button.classList.add('disabled');  // 비활성화
    button.style.pointerEvents = 'none';  // 클릭할 수 없게 만듬
  });

  // 선택된 버튼만 활성화
  selectedButton.classList.remove('disabled');
  selectedButton.classList.add('active');  // 선택된 버튼에 active 클래스 추가
}

// 결제 방식 버튼 클릭 이벤트 리스너
document.querySelector('.cash-btn').addEventListener('click', function() {
  handlePaymentButtonClick(this); // 현금 클릭 시만 활성화
  document.querySelector('.cash-btn').setAttribute('name','mb_method');
  document.querySelector('.cash-btn').setAttribute('value','현금');
});

document.querySelector('.debit-btn').addEventListener('click', function() {
  handlePaymentButtonClick(this); // 체크카드 클릭 시만 활성화
});

document.querySelector('.credit-btn').addEventListener('click', function() {
  handlePaymentButtonClick(this); // 신용카드 클릭 시만 활성화
});


