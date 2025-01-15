package hr.reservation.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UniqueReservationIdTest {

    @Test
    void generateReservationId() {
        for(int i = 0; i < 10; i++) {
            System.out.println(UniqueReservationId.generateReservationId());
        }
    }
}