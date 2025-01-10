package hr.reservation.service;

import hr.reservation.domain.ReservationForm;
import hr.reservation.domain.dto.ReservationTable;
import hr.reservation.domain.dto.TimeTable;
import hr.reservation.repository.ReservationRepository;
import hr.reservation.repository.ReservationTableRepository;
import hr.reservation.repository.TimeTableQueryRepository;
import hr.reservation.repository.TimeTableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SelectService {
    private final TimeTableQueryRepository timeTableQueryRepository;
    private final ReservationRepository reservationRepository;

    // 날짜로 시간 일정 찾기
    public List<TimeTable> findTimeTableByDate(String date) {
        return timeTableQueryRepository.findTimeTableByDate(date);
    }

    // 남은 티켓 수
    public int availableTicket(ReservationForm form) {
        return timeTableQueryRepository.availableTicket(form);
    }

    // 예약 리스트
    public List<ReservationTable> reservationList(ReservationForm form) {
        return reservationRepository.findReservationList(form);
    }
}
