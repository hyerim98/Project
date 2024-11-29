package hr.reservation;

import hr.reservation.domain.Constants;
import hr.reservation.repository.ReservationRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TestDataInit {
    private final ReservationRepository reservationRepository;
    private Map<String, Map<String, Integer>> dateMap = ReservationRepository.dateMap;

    /**
     * 임시 데이터
     */
    @PostConstruct
    public void init() {
        Map<String, Integer> timeMap1 = new HashMap<>();
        Map<String, Integer> timeMap2 = new HashMap<>();
        Map<String, Integer> timeMap3 = new HashMap<>();
        Map<String, Integer> timeMap4 = new HashMap<>();

        timeMap1.put(Constants.TIME10, 71);
        dateMap.put(Constants.DATE527, timeMap1);
        timeMap1.put(Constants.TIME12, 23);
        dateMap.put(Constants.DATE527, timeMap1);
        timeMap1.put(Constants.TIME14, 55);
        dateMap.put(Constants.DATE527, timeMap1);
        timeMap1.put(Constants.TIME17, 123);
        dateMap.put(Constants.DATE527, timeMap1);

        timeMap2.put(Constants.TIME10, 72);
        dateMap.put(Constants.DATE530, timeMap2);
        timeMap2.put(Constants.TIME12, 24);
        dateMap.put(Constants.DATE530, timeMap2);
        timeMap2.put(Constants.TIME14, 56);
        dateMap.put(Constants.DATE530, timeMap2);
        timeMap2.put(Constants.TIME17, 124);
        dateMap.put(Constants.DATE530, timeMap2);

        timeMap3.put(Constants.TIME10, 73);
        dateMap.put(Constants.DATE606, timeMap3);
        timeMap3.put(Constants.TIME12, 25);
        dateMap.put(Constants.DATE606, timeMap3);
        timeMap3.put(Constants.TIME14, 57);
        dateMap.put(Constants.DATE606, timeMap3);
        timeMap3.put(Constants.TIME17, 125);
        dateMap.put(Constants.DATE606, timeMap3);

        timeMap4.put(Constants.TIME10, 85);
        dateMap.put(Constants.DATE613, timeMap4);
        timeMap4.put(Constants.TIME12, 41);
        dateMap.put(Constants.DATE613, timeMap4);
        timeMap4.put(Constants.TIME14, 12);
        dateMap.put(Constants.DATE613, timeMap4);
        timeMap4.put(Constants.TIME17, 96);
        dateMap.put(Constants.DATE613, timeMap4);
    }
}
