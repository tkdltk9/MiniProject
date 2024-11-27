$(function() {
  // .eyes 요소(눈 아이콘)를 클릭할 때마다 아래 함수가 실행됩니다.
  $('.eyes').on('click', function() {
    // .input_wrap 요소에 active 클래스를 토글(추가 또는 제거)합니다.
    $('.input_wrap').toggleClass('active');
    
    // .input_wrap 요소에 active 클래스가 추가되었을 때 (즉, 비밀번호가 보이는 상태일 때)
    if ($('.input_wrap').hasClass('active')) {
      // 아이콘을 눈 모양에서 눈 슬래시 모양으로 변경합니다.
      $(this).find('.fa-eye').attr('class', "fas fa-eye-slash");
      // 비밀번호 입력 필드의 type을 "text"로 변경하여 비밀번호를 표시합니다.
      $('#password').attr('type', "text");
    } 
    // .input_wrap 요소에 active 클래스가 제거되었을 때 (즉, 비밀번호가 숨겨진 상태일 때)
    else {
      // 아이콘을 눈 슬래시 모양에서 눈 모양으로 다시 변경합니다.
      $(this).find('.fa-eye-slash').attr('class', "fas fa-eye");
      // 비밀번호 입력 필드의 type을 "password"로 변경하여 비밀번호를 숨깁니다.
      $('#password').attr('type', "password");
    }
  });
});