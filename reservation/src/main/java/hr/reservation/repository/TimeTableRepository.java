package hr.reservation.repository;

import hr.reservation.domain.dto.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;

// 기본 CRUD
public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {
}
