package hr.reservation.controller;

import hr.reservation.domain.ReservationForm;
import hr.reservation.domain.error.BindingResultException;
import hr.reservation.domain.error.Constants;
import hr.reservation.domain.error.ErrorResult;
import hr.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationRepository reservationRepository;

    @GetMapping
    public String reservationPage() {
        return "reservation/reservation";
    }

    @ResponseBody
    @PostMapping("/timeList")
    public String getSpareSeat(@RequestParam String date) { // @RequestParam : URL 상에서 데이터를 찾음(form 태그로 전송), @RequestBody : 객체로 전달받기 가능(body로 전송)
        JSONObject jsonObject = new JSONObject(reservationRepository.getDateMap(date));
        return jsonObject.toString();
    }

    @ResponseBody
    @PostMapping
    public String reservation(@Validated @ModelAttribute ReservationForm form, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.error("[reservation] ERROR = {}", bindingResult);
            throw new BindingResultException("valid 오류");
            //return Constants.FAIL_MSG;
        }

        reservationRepository.reserve(form);
        return Constants.SUCCESS_MSG;
    }


}
