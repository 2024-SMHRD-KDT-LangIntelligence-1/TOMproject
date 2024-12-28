
// 상태바 설정
// 상태바 진행 상태를 업데이트하는 함수
function updateProgress(percentage) {
  const progressLine = document.querySelector(".line-progress");
  progressLine.style.width = percentage + "%"; // 진행 상태 업데이트
  // 각 단계 마커의 상태를 업데이트
  const steps = document.querySelectorAll(".step");
  steps.forEach((step) => {
    const progress = parseFloat(step.getAttribute("data-progress"));
    if (progress <= percentage) {
      step.classList.add("completed"); // 진행된 마커는 초록색으로 표시
    } else {
      step.classList.remove("completed"); // 진행되지 않은 마커는 기본색으로 표시
    }
  });
}
// 상태 업데이트
updateProgress(100);

// 도넛 차트 설정
// 도넛 차트 데이터 설정
const doughnutData = [
  { goal: 100, current: 60 },
  { goal: 100, current: 75 },
  { goal: 100, current: 40 },
];

// 도넛 차트 옵션
const options = {
  responsive: true,
  cutout: "60%", // 도넛 중앙 비우기
  animation: {
    animateRotate: true,
    animateScale: true,
  },
  plugins: {
    tooltip: {
      enabled: false, // 툴팁 숨기기
    },
    legend: {
      display: false, // 범례 숨기기
    },
  },
};

// 도넛 차트를 초기화하는 함수
function createDoughnutChart(canvasId, goalValue, currentValue, chartColors) {
  const ctx = document.getElementById(canvasId).getContext("2d");
  const fillRatio = currentValue / goalValue;

  const data = {
    datasets: [
      {
        data: [fillRatio, 1 - fillRatio], // 비율에 맞게 데이터를 설정
        backgroundColor: chartColors, // 동적으로 색상을 변경
        borderWidth: 0,
      },
    ],
  };

  new Chart(ctx, {
    type: "doughnut",
    data: data,
    options: options,
  });
}

// 각 도넛 차트에 사용할 색상을 설정
const doughnutColors1 = ["#86A788", "#FFE2E2"]; // 첫 번째 도넛 차트 색상
const doughnutColors2 = ["#213555", "#F5EFE7"]; // 두 번째 도넛 차트 색상
const doughnutColors3 = ["#1A4D2E", "#E8DFCA"]; // 세 번째 도넛 차트 색상

// 각 도넛 차트 생성
createDoughnutChart(
  "doughnutChart1",
  doughnutData[0].goal,
  doughnutData[0].current,
  doughnutColors1
);
createDoughnutChart(
  "doughnutChart2",
  doughnutData[1].goal,
  doughnutData[1].current,
  doughnutColors2
);
createDoughnutChart(
  "doughnutChart3",
  doughnutData[2].goal,
  doughnutData[2].current,
  doughnutColors3
);

// 달력
const currentDate = document.querySelector(".month-name"), // 월 이름을 표시할 요소
  dayTags = document.querySelector(".days"); // 날짜를 표시할 요소
// 현재 날짜를 가져옴
let date = new Date();
let currYear = date.getFullYear(); // 현재 연도
let currMonth = date.getMonth(); // 현재 월 (0~11)
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
    liTag += `<li class="day ${isToday}">${i}</li>`; // 이번 달 날짜 추가
  }

  // 달력 내용 업데이트
  dayTags.innerHTML = liTag;
};
// 처음에 달력 렌더링
renderCalendar();

// 달력 선택시 캘린더 페이지로 이동
document.addEventListener('DOMContentLoaded', function() {
  document.getElementById('calendar').addEventListener('click', function() {
    window.location.href = "calendar";
  });
});

// 바 차트 설정

// 라인 차트 설정
