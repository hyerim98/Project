package hr.reservation.repository;

import hr.reservation.domain.dto.ReservationTable;
import org.springframework.data.jpa.repository.JpaRepository;

// 기본 CRUD
public interface ReservationTableRepository extends JpaRepository<ReservationTable, Long> {
}
