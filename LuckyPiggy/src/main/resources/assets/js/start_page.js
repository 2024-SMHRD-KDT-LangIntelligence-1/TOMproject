document.addEventListener("DOMContentLoaded", () => {
  // 이미지 슬라이드 컨테이너
  const photoContainer = document.querySelector(".photo");

  // 이미지 목록
  const images = [
    "../img/1.jpg",
    "../img/2.jpg",
    "../img/3.jpg",
    "../img/4.jpg",
    "../img/5.jpg",
    "../img/6.jpg",
  ];

  let currentIndex = 0;

  // 슬라이드 생성
  function createImageSlide() {
    photoContainer.innerHTML = ""; // 기존 이미지 제거

    // 이미지 생성
    const img = document.createElement("img");
    img.src = images[currentIndex];
    img.alt = `Image ${currentIndex + 1}`;
    photoContainer.appendChild(img);
  }

  // 슬라이드 전환
  function nextSlide() {
    currentIndex = (currentIndex + 1) % images.length;
    createImageSlide();
  }

  // 자동 슬라이드 전환 설정 -> 3초마다 자동 전환
  function autoSlide() {
    setInterval(nextSlide, 3000); // 3초마다 자동으로 전환
  }

  // 페이지 실행시 첫 번째 이미지 표시
  createImageSlide();

  // 자동 슬라이드 실행
  autoSlide();
});
