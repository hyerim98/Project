package hr.reservation;

import hr.reservation.domain.Constants;
import hr.reservation.repository.ReservationRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

// 사용 X -> 추후 삭제
@Component
@RequiredArgsConstructor
public class TestDataInit {
    private final ReservationRepository reservationRepository;
    private Map<String, Map<String, Integer>> dateMap = ReservationRepository.dateMap;

    /**
     * 임시 데이터
     */
    //@PostConstruct
    public void init() {
        Map<String, Integer> timeMap1 = new HashMap<>();
        Map<String, Integer> timeMap2 = new HashMap<>();
        Map<String, Integer> timeMap3 = new HashMap<>();
        Map<String, Integer> timeMap4 = new HashMap<>();
    }
}
