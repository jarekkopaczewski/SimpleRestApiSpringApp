package pl.edu.pwr.jkopaczewski.finanse.gui.schoolfinansegui.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.edu.pwr.jkopaczewski.finanse.gui.schoolfinansegui.dto.PaymentDto;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PaymentRestClient {
    private static final String GET_PAYMENTS_URL = "http://localhost:8080/payments";
    private static final String POST_PAYMENTS_URL = "http://localhost:8080/payments";
    private static final String POST_PAY_URL = "http://localhost:8080/payments/pay/";
    private final RestTemplate restTemplate;

    public PaymentRestClient() {
        restTemplate = new RestTemplate();
    }

    public List<PaymentDto> getPayments() {
        ResponseEntity<PaymentDto[]> payments = restTemplate.getForEntity(GET_PAYMENTS_URL, PaymentDto[].class);
        return Arrays.asList(Objects.requireNonNull(payments.getBody()));
    }

    public void addPayments(PaymentDto paymentDto){
        restTemplate.postForObject(POST_PAYMENTS_URL, paymentDto, PaymentDto.class);
    }

    public void payInstallment(Integer paymentId, String date){
        restTemplate.postForEntity(POST_PAY_URL + paymentId + "/" + date, null, null);
    }
}
