
// 상태바 설정
// 상태바 진행 상태를 업데이트하는 함수
//function updateProgress(percentage) {
  //const progressLine = document.querySelector(".line-progress");
  //progressLine.style.width = percentage + "%"; // 진행 상태 업데이트
  // 각 단계 마커의 상태를 업데이트
  //const steps = document.querySelectorAll(".step");
  //steps.forEach((step) => {
    //const progress = parseFloat(step.getAttribute("data-progress"));
    //if (progress <= percentage) {
      //step.classList.add("completed"); // 진행된 마커는 초록색으로 표시
    //} else {
     // step.classList.remove("completed"); // 진행되지 않은 마커는 기본색으로 표시
    //}
  //});
//}
// 상태 업데이트
//updateProgress(50);
//----------------------------------------------------------------------------------
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
    lastDateOfMonth = new Date(currYear, currMonth + 1, 0).getDate();

  let liTag = ""; // 날짜 목록을 저장할 변수

  // 첫 번째 날의 요일만큼 빈 공간을 추가 (이전 달 날짜는 숨기기)
  for (let i = 0; i < firstDayOfMonth; i++) {
    liTag += `<li class="inactive"></li>`; // 빈 공간 추가
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


// 달력 날짜 마우스 커서 올렸을 때 색상 변경 
//let mouseEvent = document.getElementById("days");
//mouseEvent.addEventListener("mouseover",function(event){ 
//	event.target.style.color = "rgb(251, 211, 121)";
//},false);

//mouseEvent.addEventListener("mouseout",function(event){ 
//	event.target.style.color = "";
//},false);