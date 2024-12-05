let dataKeys; // timeList 받기
let chkTime; // timeList 중에 하나 선택했는지 확인 여부
$(document).ready(function() {
    const dateInput = $("#date");

    dateInput.on('input', function (){
        const selectedDate = new Date(this.value);
        let month = (selectedDate.getMonth() + 1).toString().padStart(2, "0");
        let date = selectedDate.getDate().toString().padStart(2, "0");

        $.ajax({
          url: '/reservation/timeList',
          type: 'POST',
          data: {
            date: month + date,
          },
          dataType: 'json',
          success: function(datas) {
              dataKeys = Object.keys(datas);

              const html = `
                   <div class="rectangle" id="time10" onclick="timeTableClick('${dataKeys[0]}');">10:00<br>${datas.time10}매</div>
                   <div class="rectangle" id="time12" onclick="timeTableClick('${dataKeys[1]}');">12:00<br>${datas.time12}매</div>
                   <div class="rectangle" id="time14" onclick="timeTableClick('${dataKeys[2]}');">14:00<br>${datas.time14}매</div>
                   <div class="rectangle" id="time17" onclick="timeTableClick('${dataKeys[3]}');">17:00<br>${datas.time17}매</div>
               `;

              $(".grid-container").html(html);
              $("#timeTable").show();
          },
          error: function(xhr, status, error) {
            console.error('에러 원인 :' + error);
          }
        });


        // 평일만 선택 가능
        /*const day = selectedDate.getDay(); // 0: Sunday, 1: Monday, ...
        if (day === 0 || day === 6) {
          alert("Please select a weekday.");
          this.value = ""; // Reset the value
        }*/
    });

    // '예약하기' 버튼 클릭
    $("#reservationBtn").on('click', function (event) {
        event.preventDefault(); // 기본 폼 제출 동작 중단

        // 커스텀 동작 수행 (예: 유효성 검사 또는 AJAX 전송)
        const formData = $("#reservationForm")[0]; // DOM 객체로 변환
        const inputs = formData.querySelectorAll("input"); // 모든 input 필드 가져오기

        // 유효성 검사 실패 시 잘못된 필드 확인
        if(!formData.checkValidity()) {
            inputs.forEach((input) => {
                const errorSpan = $("#" + input.id + "Error"); // 해당 필드의 에러 메시지 요소
                if (!input.checkValidity()) {
                    // 필드 유효성 검사 실패 시 에러 메시지 표시
                    if (input.validity.valueMissing) {
                      errorSpan.text("필수 값 입니다.");
                    } else if (input.validity.patternMismatch) {
                      errorSpan.text("휴대폰 번호 형식에 맞게 입력해주세요.(010-1111-1111)");
                    } else if (input.validity.typeMismatch) {
                      errorSpan.text("이메일 형식에 맞게 입력해주세요.(abc@naver.com)");
                    } else {
                      errorSpan.text("올바른 값이 아닙니다.");
                    }
                }
                else {
                  errorSpan.text(""); // 유효하면 에러 메시지 제거
                }
            });
            return;
        }

        reservation();
    });
});

// 시간 선택
function timeTableClick(time) {
    const dataArray = dataKeys.filter(data => data !== time);
    dataArray.forEach(data => {
        $("#"+data).css('background-color', 'white');
    });

    $("#"+time).css('background-color', 'pink');

    chkTime = time;
}

function reservation() {
    let name = $("#name").val();
    let email = $("#email").val();
    let phone = $("#phone").val();
    let people = $("#people").val();
    let date = $("#date").val().split("-");

    $.ajax({
          url: '/reservation',
          type: 'POST',
          data: {
              name: name,
              email: email,
              phone: phone,
              people: people,
              date: date[1] + date[2],
              time: chkTime
          },
          dataType: 'text',
          success: function(data) {
              if(data === "SUCCESS") {
                  alert("예약이 완료되었습니다.");
              } else {
                  alert("예약에 실패하였습니다.");
              }
          },
          error: function(xhr, status, error) {
            alert("예약에 실패하였습니다.");
          }
    });
}