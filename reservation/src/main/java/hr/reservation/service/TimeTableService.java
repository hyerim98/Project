package hr.reservation.service;

import hr.reservation.domain.ReservationForm;
import hr.reservation.domain.dto.ReservationTable;
import hr.reservation.domain.dto.TimeTable;
import hr.reservation.repository.TimeTableQueryRepository;
import hr.reservation.repository.TimeTableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static hr.reservation.domain.dto.QTimeTable.timeTable;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TimeTableService {
    private final TimeTableRepository timeTableRepository;
    private final TimeTableQueryRepository timeTableQueryRepository;

    // 날짜로 시간 일정 찾기
    public List<TimeTable> findTimeTableByDate(String date) {
        return timeTableQueryRepository.findTimeTableByDate(date);
    }

    // 남은 티켓 수
    public int availableTicket(ReservationForm form) {
        return timeTableQueryRepository.availableTicket(form);
    }

    // 남은 티켓 수 업데이트
    public void updateTicket(int availableTicket, ReservationForm form) {
        timeTableQueryRepository.updateTicket(availableTicket, form);
    }
}
