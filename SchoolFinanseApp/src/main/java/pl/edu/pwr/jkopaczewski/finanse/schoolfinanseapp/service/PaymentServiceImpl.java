package pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.model.Payment;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.repository.EventRepository;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.repository.PaymentRepository;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    EventRepository eventRepository;

    public Payment addPayment(@RequestBody Payment payment){
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public void updatePayment(Payment payment) {
    }

    public ResponseEntity deletePayment(@RequestBody int paymentId) {
        paymentRepository.deleteById(paymentId);
        return ResponseEntity.ok().build();
    }

    public void setIsPayed(@PathVariable Long id, @PathVariable String date) {
        paymentRepository.findById(Math.toIntExact(id))
                .map(payment -> {
                    payment.setPayed(true);
                    payment.setPayday(date);
                    return paymentRepository.save(payment);
                });
    }
}
