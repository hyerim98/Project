package hr.reservation.domain.dto;

import jakarta.persistence.*;
import lombok.Cleanup;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity(name = "reservationtable")
public class ReservationTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_reservationtable")
    @SequenceGenerator(name = "seq_reservationtable", sequenceName = "seq_reservationtable", allocationSize = 1)
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

    @Column(name = "password", nullable = false)
    private String password;

    private LocalDateTime regDt;

    private LocalDateTime upDt;

    public ReservationTable() {
    }

    // 엔터티가 처음 저장되기 전에 호출
    @PrePersist
    public void prePersist() {
        this.regDt = LocalDateTime.now();
    }

    // 엔터티가 업데이트되기 전에 호출
    @PreUpdate
    public void preUpdate() {
        this.upDt = LocalDateTime.now();
    }
}
