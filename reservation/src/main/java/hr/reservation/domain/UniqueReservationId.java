package hr.reservation.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class UniqueReservationId {

    public static synchronized String generateReservationId() {
        // 현재 시간 가져오기
        String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());

        // UUID 생성
        UUID uuid = UUID.randomUUID();

        // 예약 번호 생성 (날짜 + UUID 일부)
        return currentDate + "-" + uuid.toString().substring(0, 8);
    }
}
