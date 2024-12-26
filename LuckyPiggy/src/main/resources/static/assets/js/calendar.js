// 변수로 저장
const currentDate = document.querySelector(".month-name"), // 월 이름을 표시할 요소
  dayTags = document.querySelector(".days"), // 날짜를 표시할 요소
  preNextBtn = document.querySelectorAll(".month-btn button"), // 이전/다음 버튼
  addExpenseButton = document.querySelector(".add-btn"); // 추가 버튼 정의

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
  document.getElementById("modal").style.display = "block";
}

// 팝업 닫기
function closePopup() {
  document.getElementById("modal").style.display = "none";
}

// 저장 버튼 클릭 시 확인 메시지
function confirmSave() {
  if (confirm("저장하시겠습니까?")) {
    // 사용자가 "OK"를 클릭한 경우 저장 처리 로직
    alert("저장되었습니다.");
    closePopup(); // 팝업 닫기
  }
  // 취소 버튼을 클릭하면 아무 동작도 하지 않음
}