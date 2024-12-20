package hr.reservation.domain.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity(name = "timetable")
public class TimeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_timetable")
    @SequenceGenerator(name = "seq_timetable", sequenceName = "seq_timetable", allocationSize = 1)
    private Long seqId;

    @Column(name = "date", length = 4, nullable = false)
    private String date;

    @Column(name = "time", length = 6, nullable = false)
    private String time;

    @Column(name = "ticket", nullable = false)
    private int ticket;

    private LocalDateTime regDt;

    private LocalDateTime upDt;

    public TimeTable() {
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
