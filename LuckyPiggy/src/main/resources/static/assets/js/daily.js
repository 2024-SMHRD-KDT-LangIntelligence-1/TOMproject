document.addEventListener("DOMContentLoaded", () => {
	const beforeBtn = document.getElementById("before-btn");
	const nextBtn = document.getElementById("next-btn");
	const weekdaysContainer = document.querySelector(".weekdays");
	const h1 = document.querySelector(".daily h1");
	const amountContainer = document.querySelector(".amount"); // 금액을 표시할 컨테이너
	
	let url = new URL(window.location.href);
	console.log(url);

	let urlParams = url.searchParams;
	console.log(urlParams);

	let currentDate = new Date(urlParams.get('date'));
	console.log('바꾼' + currentDate);
	
	let selectedDate = new Date(urlParams.get('date'));
	
	/*
	// 오늘 날짜
	let currentDate = new Date();
	console.log('최근날짜' + currentDate);
	let selectedDate = new Date();
	*/
	
	/*	let currYear = date.getFullYear(); // 현재 연도
		let currMonth = date.getMonth(); // 현재 월 (0~11)*/



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
			let day = document.createElement("li");
			day.classList.add('day');
			const date = weekDates[i];

			// 날짜를 '일 22' 형식으로 표시
			day.textContent = `${weekdays[date.getDay()]} ${date.getDate()}`;

			// 클릭 시 날짜 선택
			day.addEventListener("click", () => handleDateClick(date.getFullYear(), date.getMonth(), date.getDate()));

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
	function handleDateClick(year, month, day) {


		const dateStr = `${year}-${String(month + 1).padStart(2, "0")}-${String(
			day
		).padStart(2, "0")}`;
		selectedDate = new Date(dateStr); // 선택된 날짜 저장
		//console.log(selectedDate);
		//currentDate = new Date(date); // 주간 달력도 선택한 날짜 기준으로 이동
		//updateWeeklyCalendar(); // 달력 업데이트

		window.location.href = `daily?date=${dateStr}`;
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
		h1.textContent = `${currentDate.getMonth() + 1}월`;
	}

	// 다음 주로 이동하는 함수
	function goToNextWeek() {
		currentDate.setDate(currentDate.getDate() + 7);
		updateWeeklyCalendar();
		h1.textContent = `${currentDate.getMonth() + 1}월`;
	}

	// 버튼 클릭 이벤트 리스너 등록
	beforeBtn.addEventListener("click", goToPreviousWeek);
	nextBtn.addEventListener("click", goToNextWeek);

	// 초기 달력 표시
	updateWeeklyCalendar();
});

// 팝업 열기
function openPopup() {
	document.getElementById("modal").style.display = "block";
	document.getElementById("daily").style.pointerEvents = 'none'; // 데일리 클릭 비활성화
}

// 팝업 닫기
function closePopup() {
	document.getElementById("modal").style.display = "none";
	document.getElementById("daily").style.pointerEvents = 'auto'; // 데일리 클릭 활성화
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
	document.querySelector('#money').setAttribute('name', 'mb_ic');
});

// 출금 버튼 클릭 이벤트
document.querySelector('.expense-btn').addEventListener('click', function() {
	handleButtonClick(this); // 클릭된 버튼만 활성화
	showAllPayments(); // 출금 클릭 시 모든 결제 방식 버튼 보이게
	document.querySelector('#money').setAttribute('name', 'mb_amount');
});

// 저장 버튼 클릭 시 초기화
function confirmSave() {
	const result = confirm("저장하시겠습니까?");

	if (result) {
		// 사용자가 "OK"를 클릭한 경우 저장 처리 로직
		alert("저장되었습니다.");

		// 내용 초기화
		resetForm(); // 폼 초기화 함수 호출

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
});

document.querySelector('.debit-btn').addEventListener('click', function() {
	handlePaymentButtonClick(this); // 체크카드 클릭 시만 활성화
});

document.querySelector('.credit-btn').addEventListener('click', function() {
	handlePaymentButtonClick(this); // 신용카드 클릭 시만 활성화
});

