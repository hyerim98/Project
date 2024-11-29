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
                   <div class="rectangle" id="time10" onclick="timeTableClick('${dataKeys[0]}');">10:00<br>(${datas.time10}/150)</div>
                   <div class="rectangle" id="time12" onclick="timeTableClick('${dataKeys[1]}');">12:00<br>(${datas.time12}/150)</div>
                   <div class="rectangle" id="time14" onclick="timeTableClick('${dataKeys[2]}');">14:00<br>(${datas.time14}/150)</div>
                   <div class="rectangle" id="time17" onclick="timeTableClick('${dataKeys[3]}');">17:00<br>(${datas.time17}/150)</div>
               `;

              $(".grid-container").html(html);
              $("#timeTable").show();
          },
          error: function(xhr, status, error) {
            console.error('Error:', error);
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
    $("#reservationBtn").on('click', function () {
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

    if(name === "" || email === "" || phone === "" || people === "" || date === "") {
        alert("값을 입력해주세요.");
        return;
    }
    if(chkTime === undefined) {
        alert("시간을 선택해주세요.");
        return;
    }

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
              if(data === "fail") {
                  alert("예약에 실패하였습니다.");
              } else {
                  alert("예약이 완료되었습니다.");
              }
          },
          error: function(xhr, status, error) {
            console.error('Error:', error);
          }
    });
}