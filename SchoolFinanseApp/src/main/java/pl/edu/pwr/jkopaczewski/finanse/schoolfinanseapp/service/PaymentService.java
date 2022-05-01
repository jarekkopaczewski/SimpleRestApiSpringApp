package pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    Payment addPayment(Payment payment);
    List<Payment> getAllPayments();
    void updatePayment(Payment payment);
    ResponseEntity deletePayment(int paymentId);
    void setIsPayed(Long id, String date);
}
