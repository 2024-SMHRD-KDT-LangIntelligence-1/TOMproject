// 상태바 이동
let currentProgress = 0; // 초기 진행 상태
const progressLine = document.querySelector(".line-progress");
const steps = document.querySelectorAll(".step");

function updateProgress(percentage) {
  progressLine.style.width = percentage + "%"; // 진행 상태 업데이트
  steps.forEach((step) => {
    const progress = parseFloat(step.getAttribute("data-progress"));
    if (progress <= percentage) {
      step.classList.add("completed");
    } else {
      step.classList.remove("completed");
    }
  });
}

// 30초마다 진행 상태 업데이트
setInterval(() => {
  currentProgress += 25; // 25%씩 증가
  if (currentProgress > 100) currentProgress = 0; // 100%를 초과하면 초기화
  updateProgress(currentProgress);
}, 30000); // 30초 간격으로 수정
//-------------------------------------------------------------------------------------
// 달력
const dayTags = document.querySelector(".days"); // 날짜를 표시할 요소

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

document.addEventListener('DOMContentLoaded', function() {
	let calendara = document.getElementById('calclick');

	// 'calendar' 영역을 클릭하면 특정 URL로 이동
	calendara.addEventListener('click', function() {
		console.log('calendar 클릭 이벤트 발생');
		window.location.href = '/calendar?month=' + (currMonth + 1);
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