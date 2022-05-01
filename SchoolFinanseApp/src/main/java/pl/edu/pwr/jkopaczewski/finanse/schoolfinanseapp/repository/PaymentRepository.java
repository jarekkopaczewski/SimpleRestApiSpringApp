package pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
