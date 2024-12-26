document.addEventListener("DOMContentLoaded", () => {
  const beforeBtn = document.getElementById("before-btn");
  const nextBtn = document.getElementById("next-btn");
  const weekdaysContainer = document.querySelector(".weekdays");
  const h1 = document.querySelector(".daily h1");
  const amountContainer = document.querySelector(".amount"); // 금액을 표시할 컨테이너

  let currentDate = new Date(); // 오늘 날짜
  let selectedDate = new Date(); // 기본 선택 날짜는 오늘

  // 요일 이름 배열 (일 ~ 토)
  const weekdays = ["일", "월", "화", "수", "목", "금", "토"];

  // 임의의 수입/지출 데이터 예시 (실제 데이터를 동적으로 처리할 경우에는 서버나 다른 방법으로 데이터를 가져옵니다)
  const transactions = {
    "2024-12-22": { income: 50000, expense: 20000 },
    "2024-12-23": { income: 100000, expense: 15000 },
    "2024-12-24": { income: 30000, expense: 10000 },
    // ... (다른 날짜에 대한 예시)
  };

  // 주간 달력을 업데이트하는 함수
  function updateWeeklyCalendar() {
    // 현재 날짜를 기준으로 해당 주의 날짜들 생성
    const startOfWeek = new Date(currentDate);
    const dayOfWeek = startOfWeek.getDay(); // 오늘의 요일 (0: 일요일, 1: 월요일, ..., 6: 토요일)

    // 월요일부터 시작하도록 날짜 조정
    const adjustment = dayOfWeek === 0 ? -6 : 1 - dayOfWeek;
    startOfWeek.setDate(currentDate.getDate() + adjustment); // 월요일로 이동

    // 날짜 배열을 저장할 변수
    const weekDates = [];

    // 주간 날짜 생성
    for (let i = 0; i < 7; i++) {
      const date = new Date(startOfWeek);
      date.setDate(startOfWeek.getDate() + i);
      weekDates.push(date);
    }

    // 주간 날짜를 표시
    weekdaysContainer.innerHTML = ""; // 기존 내용 비우기
    for (let i = 0; i < 7; i++) {
      const day = document.createElement("li");
      const date = weekDates[i];

      // 날짜를 '일 22' 형식으로 표시
      day.textContent = `${weekdays[date.getDay()]} ${date.getDate()}`;

      // 클릭 시 날짜 선택
      day.addEventListener("click", () => handleDateClick(date));

      // 선택된 날짜 강조
      if (date.toDateString() === selectedDate.toDateString()) {
        day.classList.add("selected");
      }

      weekdaysContainer.appendChild(day);
    }

    // 선택된 날짜의 월을 헤더에 표시
    const selectedMonth = selectedDate.getMonth() + 1; // 0-based month (0 = January, 11 = December)
    h1.textContent = `${selectedMonth}월`;

    // 선택된 날짜에 맞는 수입/지출 금액을 표시
    displayIncomeExpense(selectedDate);
  }

  // 날짜 클릭 시 선택된 날짜를 변경하는 함수
  function handleDateClick(date) {
    selectedDate = new Date(date); // 선택된 날짜 저장
    currentDate = new Date(date); // 주간 달력도 선택한 날짜 기준으로 이동
    updateWeeklyCalendar(); // 달력 업데이트
  }

  // 선택된 날짜에 대한 수입/지출 금액을 표시하는 함수
  function displayIncomeExpense(date) {
    const dateString = date.toISOString().split("T")[0]; // 날짜를 "yyyy-mm-dd" 형식으로 변환

    // 선택된 날짜에 해당하는 수입과 지출 금액을 가져옴
    const transaction = transactions[dateString];

    // 수입과 지출이 없을 경우 기본 값 설정
    const income = transaction ? transaction.income : 0;
    const expense = transaction ? transaction.expense : 0;

    // 금액만 표시 (수입과 지출에 각각 클래스를 추가하여 색상을 다르게 적용)
    amountContainer.innerHTML = `
      <p class="income">${income.toLocaleString()}</p>
      <p class="expense">${expense.toLocaleString()}</p>
    `;
  }

  // 이전 주로 이동하는 함수
  function goToPreviousWeek() {
    currentDate.setDate(currentDate.getDate() - 7);
    updateWeeklyCalendar();
	h1.textContent = `${currentDate.getMonth()+1}월`;
  }

  // 다음 주로 이동하는 함수
  function goToNextWeek() {
    currentDate.setDate(currentDate.getDate() + 7);
    updateWeeklyCalendar();
	h1.textContent = `${currentDate.getMonth()+1}월`;
  }

  // 버튼 클릭 이벤트 리스너 등록
  beforeBtn.addEventListener("click", goToPreviousWeek);
  nextBtn.addEventListener("click", goToNextWeek);

  // 초기 달력 표시
  updateWeeklyCalendar();
});

// 추가 버튼 클릭 시 (임시로 alert로 지출 추가)
addExpenseButton.addEventListener("click", () => {
  alert("또 돈썼지!!"); // 알림창을 띄움
});
