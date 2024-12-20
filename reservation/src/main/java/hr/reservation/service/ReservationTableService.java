package hr.reservation.service;

import hr.reservation.domain.AESPasswordEncryption;
import hr.reservation.domain.ReservationForm;
import hr.reservation.domain.dto.ReservationTable;
import hr.reservation.repository.ReservationTableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ReservationTableService {
    private final ReservationTableRepository reservationTableRepository;

    // 예약
    public void reserve(ReservationForm form) {
        ReservationTable reservationTable = new ReservationTable();
        reservationTable.setDate(form.getDate());
        reservationTable.setName(form.getName());
        reservationTable.setTicket(form.getPeople());
        reservationTable.setPhone(form.getPhone());
        reservationTable.setEmail(form.getEmail());
        reservationTable.setTime(form.getTime());
        reservationTable.setPassword(AESPasswordEncryption.encrypt(form.getPassword()));

        reservationTableRepository.save(reservationTable);
    }
}
