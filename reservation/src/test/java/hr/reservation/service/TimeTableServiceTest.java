package hr.reservation.service;

import hr.reservation.domain.ReservationForm;
import hr.reservation.repository.TimeTableQueryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TimeTableServiceTest {
    @Autowired
    TimeTableService timeTableService;

    @Test
    void findTimeTableByDate() {
        System.out.println("findTimeTableByDate() : " + timeTableService.findTimeTableByDate("0606"));
    }

    @Test
    void availableTicket() {
        ReservationForm form = new ReservationForm();
        form.setTime("time10");
        form.setDate("0610");
        System.out.println("availableTicket() : " + timeTableService.availableTicket(form));
    }

}