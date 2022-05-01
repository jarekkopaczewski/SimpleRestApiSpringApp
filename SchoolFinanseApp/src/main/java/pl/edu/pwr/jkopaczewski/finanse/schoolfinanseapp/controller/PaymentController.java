package pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.dto.PaymentDto;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.model.Payment;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.service.PaymentService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @PostMapping("/payments")
    public Payment addPayment(@RequestBody Payment payment){
        return paymentService.addPayment(payment);
    }

    /**
     * zamiana listy na strumień, a następnie zamiana Payment na PaymentDto za pomocą map
     * @return
     */
    @GetMapping("/payments")
    public List<PaymentDto> getAllPayments() {
        return paymentService.getAllPayments()
                .stream()
                .map(PaymentDto::of)
                .collect(Collectors.toList());
    }

    @PutMapping("/payments/{id}")
    public void updatePayment(Payment payment) {}

    @PostMapping("/payments/pay/{id}/{date}")
    public void setIsPayed(@PathVariable(name = "id") Integer id, @PathVariable(name = "date") String date ){ paymentService.setIsPayed(Long.valueOf((id)), date); }

    @DeleteMapping("/payments")
    public ResponseEntity deletePayment(@RequestBody int paymentId) { return paymentService.deletePayment(paymentId); }
}
