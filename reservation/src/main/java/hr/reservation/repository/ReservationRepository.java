package hr.reservation.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hr.reservation.domain.AESPasswordEncryption;
import hr.reservation.domain.ReservationForm;
import hr.reservation.domain.dto.ReservationTable;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static hr.reservation.domain.dto.QReservationTable.reservationTable;

// 동적 쿼리
@Slf4j
@Repository
public class ReservationRepository {
    private final JPAQueryFactory query;

    public ReservationRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    // 예약 리스트 확인
    public List<ReservationTable> findReservationList(ReservationForm form) {
        String name = form.getName();
        String phone = form.getPhone();
        String password = AESPasswordEncryption.encrypt(form.getPassword());

        return query.select(reservationTable).from(reservationTable)
                .where(reservationTable.name.eq(name).and(reservationTable.phone.eq(phone)).and(reservationTable.password.eq(password))).fetch();
    }
}
