package pl.edu.pwr.jkopaczewski.finanse.gui.schoolfinansegui.table;

import lombok.Getter;
import lombok.Setter;
import pl.edu.pwr.jkopaczewski.finanse.gui.schoolfinansegui.dto.PaymentDto;

@Getter
@Setter
public class PaymentTableModel {
    private final Long paymentId;
    private final String paymentDeadline;
    private final String payday;
    private final Long paymentAmount;
    private final Long instalmentNumber;
    private final Boolean isPayed;
    private final Long eventId;
    private final Long personId;

    public PaymentTableModel(Long paymentId, String paymentDeadline, String payday, Long paymentAmount, Long instalmentNumber, Boolean isPayed, Long eventId, Long personId) {
        this.paymentId = paymentId;
        this.paymentDeadline = paymentDeadline;
        this.payday = payday;
        this.paymentAmount = paymentAmount;
        this.instalmentNumber = instalmentNumber;
        this.isPayed = isPayed;
        this.personId = personId;
        this.eventId = eventId;
    }

    public static PaymentTableModel of(PaymentDto dto) {
        return new PaymentTableModel(dto.getPaymentId(), dto.getPaymentDeadline(), dto.getPayday(),
                dto.getPaymentAmount(), dto.getInstalmentNumber(), dto.isPayed(), dto.getPaymentEvent().getEventId(), dto.getPaymentPerson().getPersonId());
    }
}
