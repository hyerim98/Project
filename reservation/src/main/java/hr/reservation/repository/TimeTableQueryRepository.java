package hr.reservation.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hr.reservation.domain.ReservationForm;
import hr.reservation.domain.dto.QTimeTable;
import hr.reservation.domain.dto.TimeTable;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static hr.reservation.domain.dto.QTimeTable.*;

// 동적 쿼리, 복잡한 쿼리
@Slf4j
@Repository
public class TimeTableQueryRepository {
    private final JPAQueryFactory query;

    public TimeTableQueryRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    // 날짜로 시간 일정 찾기
    public List<TimeTable> findTimeTableByDate(String date) {
        return query.select(timeTable).from(timeTable).where(timeTable.date.eq(date)).fetch();
    }

    // 남은 티켓 수
    public int availableTicket(ReservationForm form) {
        String date = form.getDate();
        String time = form.getTime();

        return query.select(timeTable.ticket).from(timeTable).where(timeTable.date.eq(date).and(timeTable.time.eq(time))).fetch().get(0);
    }

    // 남은 티켓 수 업데이트
    public void updateTicket(int availableTicket, ReservationForm form) {
        String date = form.getDate();
        String time = form.getTime();
        int ticket = form.getPeople();

        query.update(timeTable)
                .set(timeTable.ticket, availableTicket - ticket).set(timeTable.upDt, LocalDateTime.now())
                .where(timeTable.date.eq(date).and(timeTable.time.eq(time)))
                .execute();
    }



}
