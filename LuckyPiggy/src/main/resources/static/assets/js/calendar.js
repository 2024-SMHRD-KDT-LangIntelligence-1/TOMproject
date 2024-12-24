document.addEventListener("DOMContentLoaded", () => {
  let currentDate = new Date();
  const monthLabel = document.querySelector("h1");
  const prevMonthBtn = document.getElementById("before-month");
  const nextMonthBtn = document.getElementById("next-month");
  const calendarBody = document.getElementById("calendar-body");
  const expenseData = {}; // 날짜별 지출 내역 저장
  const addExpenseButton = document.querySelector(".add-btn");

  // 달력 업데이트 함수
  function updateCalendar() {
    const year = currentDate.getFullYear();
    const month = currentDate.getMonth();
    monthLabel.textContent = `${month + 1}월`;

    const firstDayOfMonth = new Date(year, month, 1);
    const lastDayOfMonth = new Date(year, month + 1, 0);
    const daysInMonth = lastDayOfMonth.getDate();
    const startingDay = firstDayOfMonth.getDay();

    calendarBody.innerHTML = ""; // 기존 테이블 내용 삭제

    let row = document.createElement("tr");

    // 첫 번째 주의 빈 칸 추가
    for (let i = 0; i < startingDay; i++) {
      row.appendChild(document.createElement("td")); // 공백 추가
    }

    // 날짜 채우기
    for (let day = 1; day <= daysInMonth; day++) {
      if ((startingDay + day - 1) % 7 === 0 && day !== 1) {
        calendarBody.appendChild(row); // 이전 주 추가
        row = document.createElement("tr"); // 새로운 주 시작
      }

      const cell = createDateCell(year, month, day);
      row.appendChild(cell);
    }

    // 마지막 주가 7일로 채워지지 않으면 빈 칸 추가
    const remainingDays = (startingDay + daysInMonth - 1) % 7;
    if (remainingDays !== 6) {
      addEmptyCells(row, 6 - remainingDays);
    }

    calendarBody.appendChild(row); // 마지막 주 추가
  }

  // 날짜 셀 생성
  function createDateCell(year, month, day) {
    const cell = document.createElement("td");
    cell.textContent = day;
    cell.dataset.date = `${year}-${month + 1}-${day}`;
    cell.addEventListener("click", () => redirectToDailyPage(year, month, day)); // 날짜 클릭 시 daily.html로 이동
    return cell;
  }

  // 빈 셀 추가
  function addEmptyCells(row, count) {
    for (let i = 0; i < count; i++) {
      row.appendChild(document.createElement("td")); // 빈 칸 추가
    }
  }

  // 날짜 클릭 시 daily.html 페이지로 이동
  function redirectToDailyPage(year, month, day) {
    // 날짜를 YYYY-MM-DD 형식으로 변환
    const dateStr = `${year}-${String(month + 1).padStart(2, "0")}-${String(
      day
    ).padStart(2, "0")}`;
    window.location.href = `daily.html?date=${dateStr}`; // URL에 날짜를 쿼리 파라미터로 전달
  }

  // 이전/다음 월로 이동하는 버튼 클릭 이벤트
  prevMonthBtn.addEventListener("click", () => {
    currentDate.setMonth(currentDate.getMonth() - 1);
    updateCalendar();
  });

  nextMonthBtn.addEventListener("click", () => {
    currentDate.setMonth(currentDate.getMonth() + 1);
    updateCalendar();
  });

  // 추가 버튼 클릭 이벤트 (임시로 alert로 지출 추가)
  addExpenseButton.addEventListener("click", () => {
    alert("또 돈썼지!!");
  });

  // 달력 초기화
  updateCalendar();
});
