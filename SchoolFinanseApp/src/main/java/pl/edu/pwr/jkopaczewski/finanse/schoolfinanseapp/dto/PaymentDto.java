package pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.dto;

import lombok.Data;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.model.Event;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.model.Payment;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.model.Person;

@Data
public class PaymentDto {
    private int paymentId;
    private String paymentDeadline;
    private String payday;
    private long paymentAmount;
    private long instalmentNumber;
    private boolean isPayed;
    private Person paymentPerson;
    private Event paymentEvent;

    public static PaymentDto of(Payment payment){
        PaymentDto dto = new PaymentDto();
        dto.setPayday(payment.getPayday());
        dto.setPaymentId(payment.getPaymentId());
        dto.setPaymentDeadline(payment.getPaymentDeadline());
        dto.setPaymentAmount(payment.getPaymentAmount());
        dto.setPayed(payment.isPayed());
        dto.setPaymentPerson(payment.getPaymentPerson());
        dto.setPaymentEvent(payment.getPaymentEvent());
        dto.setInstalmentNumber(payment.getInstalmentNumber());
        return dto;
    }
}
