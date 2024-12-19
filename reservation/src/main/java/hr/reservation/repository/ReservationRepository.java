package hr.reservation.repository;

import hr.reservation.domain.ReservationForm;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// 사용 X - 추후에 삭제
@Repository
public class ReservationRepository {
    public static final Map<String, Map<String, Integer>> dateMap = new ConcurrentHashMap<>();
    private static final Map<Long, ReservationForm> reserveMap = new ConcurrentHashMap<>();
    private Long sequenceId = 0L;

    public Map<String, Integer> getDateMap(String date) {
        return dateMap.get(date);
    }

    // 예약
    public void reserve(ReservationForm reservation) {
        Map<String, Integer> timeMap = dateMap.get(reservation.getDate());
        int remainTicket = availablePeople(reservation) - reservation.getPeople();
        timeMap.put(reservation.getTime(), remainTicket);

        reserveMap.put(++sequenceId, reservation);
    }

    // 예약 가능 인원
    public int availablePeople(ReservationForm reservation) {
        Map<String, Integer> timeMap = dateMap.get(reservation.getDate());
        return timeMap.get(reservation.getTime());
    }

    public void cancel(String time, int num) {

    }
}
