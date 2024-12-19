package hr.reservation.domain.dto;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "timetable")
public class TimeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_timetable")
    @SequenceGenerator(name = "seq_timetable", sequenceName = "seq_timetable", allocationSize = 1)
    @Column(name = "seqid")
    private Long seqId;

    @Column(name = "date", length = 4, nullable = false)
    private String date;

    @Column(name = "time", length = 6, nullable = false)
    private String time;

    @Column(name = "ticket", nullable = false)
    private int ticket;

    public TimeTable() {
    }
}
