package pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

@Setter
@Getter
@Entity
@Table(name = "payments")
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;
    private String paymentDeadline;
    private String payday;
    private long paymentAmount;
    private long instalmentNumber;
    private boolean isPayed;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "personId")
    private Person paymentPerson;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "eventId")
    private Event paymentEvent;

    public Payment(String paymentDeadline, long paymentAmount, long instalmentNumber, Event paymentEvent, Person paymentPerson) {
        this.paymentDeadline = paymentDeadline;
        this.paymentAmount = paymentAmount;
        this.instalmentNumber = instalmentNumber;
        this.payday = "";
        this.isPayed = false;
        this.paymentPerson = paymentPerson;
        this.paymentEvent = paymentEvent;
    }

    public Payment(){}
}
