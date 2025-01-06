let dataKeys = ['time10', 'time12', 'time14', 'time17']; // timeList 받기
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
              const html = `
                   <div class="rectangle" id="time10" onclick="timeTableClick('${datas[0].time}');">10:00<br>${datas[0].ticket}매</div>
                   <div class="rectangle" id="time12" onclick="timeTableClick('${datas[1].time}');">12:00<br>${datas[1].ticket}매</div>
                   <div class="rectangle" id="time14" onclick="timeTableClick('${datas[2].time}');">14:00<br>${datas[2].ticket}매</div>
                   <div class="rectangle" id="time17" onclick="timeTableClick('${datas[3].time}');">17:00<br>${datas[3].ticket}매</div>
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
                        if(input.id === "phone") {
                            errorSpan.text("휴대폰 번호 형식에 맞게 입력해주세요.(010-1111-1111)");
                        } else {
                            errorSpan.text("비밀번호는 최소 8자, 하나 이상의 대문자, 소문자, 숫자 및 특수 문자가 포함되어야 합니다.");
                        }
                    } else if (input.validity.typeMismatch) {
                      errorSpan.text("이메일 형식에 맞게 입력해주세요.(abc@naver.com)");
                    } else if(input.validity.tooShort) {
                        errorSpan.text("비밀번호 8자리 이상으로 설정해주세요.");
                    } else if(input.validity.tooLong) {
                        errorSpan.text("비밀번호 20자리 이하로 설정해주세요.");
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

    // '예약확인/변경/취소' 버튼 클릭
    $("#reservationChkBtn").on('click', function (event) {
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
                        if(input.id === "phone") {
                            errorSpan.text("휴대폰 번호 형식에 맞게 입력해주세요.(010-1111-1111)");
                        } else {
                            errorSpan.text("비밀번호는 최소 8자, 하나 이상의 대문자, 소문자, 숫자 및 특수 문자가 포함되어야 합니다.");
                        }
                    }
                }
                else {
                  errorSpan.text(""); // 유효하면 에러 메시지 제거
                }
            });
            return;
        }
        reservationChk();
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

async function reservation() {
    let name = $("#name").val();
    let email = $("#email").val();
    let phone = $("#phone").val();
    let people = $("#people").val();
    let date = $("#date").val().split("-");
    let password = $("#password").val();

    if(chkTime === undefined) {
        alert("시간을 선택해주세요");
        return;
    }

    $("#time").val(chkTime);

    let data = {
        name: name,
        email: email,
        phone: phone,
        people: people,
        date: date[1] + date[2],
        time: chkTime,
        password: password
    };


    try {
        // 예약 요청
        const reservationRes = await $.ajax({
            url: '/reservation',
            type: 'POST',
            data: data,
            dataType: 'json'
        });

        // 예약 요청 응답 받은 후
        const code = reservationRes.code;
        if(code === "2000") {
            alert("예약이 완료되었습니다.");
            let reservationForm = document.getElementById("reservationForm");
            reservationForm.method = "POST";
            reservationForm.action = "/reservation/confirm";
            reservationForm.submit();
        } else if (code === "9001") {
            alert("예약 가능 인원을 초과하였습니다.");
            $("#people").val("");
        } else {
            alert("예약에 실패하였습니다.");
            location.reload();
        }
    } catch (error) {
        console.log(error);
        alert("예약에 실패하였습니다.");
        location.reload();
    }

    /*$.ajax({
          url: '/reservation',
          type: 'POST',
          data: {
              name: name,
              email: email,
              phone: phone,
              people: people,
              date: date[1] + date[2],
              time: chkTime,
              password: password
          },
          dataType: 'json',
          success: function(data) {
              const code = data.code;

              if(code === "2000") {
                  alert("예약이 완료되었습니다.");
                  location.href = '/reservation/confirm';
              } else if (code === "9001") {
                  alert("예약 가능 인원을 초과하였습니다.");
                  $("#people").val("");
              } else {
                  alert("예약에 실패하였습니다.");
                  location.reload();
              }
          },
          error: function(xhr, status, error) {
            alert("예약에 실패하였습니다.");
            location.reload();
          }
    });*/
}

async function reservationChk() {
    let name = $("#name").val();
    let phone = $("#phone").val();
    let password = $("#password").val();

    let data = {
        name: name,
        phone: phone,
        password: password
    };

    try {
        // 예약 확인 요청
        const reservationRes = await $.ajax({
            url: '/reservation/chk',
            type: 'POST',
            data: data,
            dataType: 'json'
        });

        // 예약 확인 요청 응답 받은 후
        const code = reservationRes.code;
        if(code === "2000") {

        } else {
            alert("예약 확인에 실패하였습니다.");
            location.reload();
        }
    } catch (error) {
        console.log(error);
        alert("예약 확인에 실패하였습니다.");
        location.reload();
    }
}