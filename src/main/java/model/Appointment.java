package model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Appointment {
    @Id
    private int id;
    @Column(name = "pesel")
    private String pesel;
    @Column(name = "doctor_id")
    private int doctorId;
    @Column(name = "date_time")
    private LocalDateTime dateTime;

//    @ManyToOne
//@JoinColumn(name = "")
}
