package pl.edu.pwr.jkopaczewski.finanse.gui.schoolfinansegui.dto;

import lombok.Data;

@Data
public class PaymentDto {
    private long paymentId;
    private String paymentDeadline;
    private String payday;
    private long paymentAmount;
    private long instalmentNumber;
    private boolean isPayed;
    private PersonDto paymentPerson;
    private EventDto paymentEvent;
}
