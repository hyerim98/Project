package hr.reservation.controller;

import hr.reservation.domain.ReservationForm;
import hr.reservation.domain.UniqueReservationId;
import hr.reservation.domain.dto.TimeTable;
import hr.reservation.domain.error.BindingResultException;
import hr.reservation.domain.error.Constants;
import hr.reservation.domain.error.ErrorResult;
import hr.reservation.service.ReservationService;
import hr.reservation.service.SelectService;
import hr.reservation.service.mail.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final SelectService selectService;
    private final ReservationService reservationService;
    private final EmailService emailService;

    @GetMapping
    public String reservationPage() {
        return "reservation/reservation";
    }

    @ResponseBody
    @PostMapping("/timeList")
    public List<TimeTable> getSpareSeat(@RequestParam String date) { // @RequestParam : URL 상에서 데이터를 찾음(form 태그로 전송), @RequestBody : 객체로 전달받기 가능(body로 전송)
        return selectService.findTimeTableByDate(date);
    }

    @ResponseBody
    @PostMapping
    public ErrorResult reservation(@Validated @ModelAttribute ReservationForm form, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.error("[reservation] ERROR = {}", bindingResult);
            throw new BindingResultException("valid 오류");
        }

        // 예약 인원이 최대 가능 수용을 초과한 경우
        int availablePeople = selectService.availableTicket(form);
        if(availablePeople < form.getPeople()) {
            log.info("[reservation] 예약 가능 인원을 초과하였습니다.({})",form.getName());
            return new ErrorResult(Constants.RESERVATION_CAPA_EXCEED_CODE, Constants.RESERVATION_CAPA_EXCEED_MSG);
        }

        form.setReservationId(UniqueReservationId.generateReservationId());
        reservationService.reserve(form);
        reservationService.updateTicket(availablePeople, form);

        // 메일 발송
        emailService.sendAuthCode(form.getEmail(), form);

        return new ErrorResult(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG);
    }

    @PostMapping("/confirm")
    public String reservationConfirm(@ModelAttribute ReservationForm form,  Model model) {
        model.addAttribute("name", form.getName());
        model.addAttribute("date", form.getDate() + " " + form.getTime().substring(4) + "시");
        model.addAttribute("people", form.getPeople() + "명");
        return "reservation/reservationConfirm";
    }

    @GetMapping("/chk")
    public String reservationChk() {
        return "reservation/reservationChk";
    }

    @PostMapping("/chk")
    public String reservationList(@ModelAttribute ReservationForm form) {
        log.info("hrdel : " + selectService.reservationList(form));
        return "reservation/reservationList";
    }


}
