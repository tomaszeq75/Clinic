package model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Appointment {
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "pesel")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private LocalDateTime dateTime;
}
