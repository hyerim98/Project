package hr.reservation.controller;

import hr.reservation.domain.ReservationForm;
import hr.reservation.domain.dto.TimeTable;
import hr.reservation.domain.error.BindingResultException;
import hr.reservation.domain.error.Constants;
import hr.reservation.domain.error.ErrorResult;
import hr.reservation.repository.ReservationRepository;
import hr.reservation.service.ReservationTableService;
import hr.reservation.service.TimeTableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final TimeTableService timeTableService;
    private final ReservationTableService reservationTableService;

    @GetMapping
    public String reservationPage() {
        return "reservation/reservation";
    }

    @ResponseBody
    @PostMapping("/timeList")
    public List<TimeTable> getSpareSeat(@RequestParam String date) { // @RequestParam : URL 상에서 데이터를 찾음(form 태그로 전송), @RequestBody : 객체로 전달받기 가능(body로 전송)
        return timeTableService.findTimeTableByDate(date);
    }

    @ResponseBody
    @PostMapping
    public ErrorResult reservation(@Validated @ModelAttribute ReservationForm form, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.error("[reservation] ERROR = {}", bindingResult);
            throw new BindingResultException("valid 오류");
        }

        // 예약 인원이 최대 가능 수용을 초과한 경우
        int availablePeople = timeTableService.availableTicket(form);
        if(availablePeople < form.getPeople()) {
            log.info("[reservation] 예약 가능 인원을 초과하였습니다.({})",form.getName());
            return new ErrorResult(Constants.RESERVATION_CAPA_EXCEED_CODE, Constants.RESERVATION_CAPA_EXCEED_MSG);
        }

        reservationTableService.reserve(form);
        timeTableService.updateTicket(availablePeople, form);
        return new ErrorResult(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG);
    }

    @GetMapping("/confirm")
    public String reservationConfirm() {
        return "reservation/reservationConfirm";
    }


}
