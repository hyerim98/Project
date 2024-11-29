package hr.reservation.controller;

import hr.reservation.domain.ReservationForm;
import hr.reservation.repository.ReservationRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String getSpareSeat(@RequestParam String date) {
        JSONObject jsonObject = new JSONObject(reservationRepository.getDateMap(date));
        return jsonObject.toString();
    }

    @ResponseBody
    @PostMapping
    public String reservation(@Valid @ModelAttribute ReservationForm form, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "fail";
        }

        reservationRepository.reserve(form);
        return "success";
    }


}
