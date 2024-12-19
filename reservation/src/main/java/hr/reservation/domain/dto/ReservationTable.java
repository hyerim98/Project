package hr.reservation.domain.dto;

import jakarta.persistence.*;
import lombok.Cleanup;
import lombok.Data;

@Data
@Entity(name = "reservationtable")
public class ReservationTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_reservationtable")
    @SequenceGenerator(name = "seq_reservationtable", sequenceName = "seq_reservationtable", allocationSize = 1)
    @Column(name = "seqid")
    private Long seqId;

    @Column(name = "date", length = 4, nullable = false)
    private String date;

    @Column(name = "time", length = 6, nullable = false)
    private String time;

    @Column(name = "name", length = 10, nullable = false)
    private String name;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "phone", length = 13, nullable = false)
    private String phone;

    @Column(name = "ticket", nullable = false)
    private int ticket;

    public ReservationTable() {
    }
}
