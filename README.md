# Table of contents
* [General info](#general-info)
* [Built With](#built-with)
* [GUI](#gui)
* [Example functions from event controller class](#example-functions-from-event-controller-class)
* [Part of payment class table](#part-of-payment-class-table)
* [Event table model](#event-table-model)
* [Payment rest client](#payment-rest-client)
* [License](#license)

# About The Project

## General info

<p class="text-justify">
A simple rest api application for managing payments at school created using Spring. A separate JavaFx application using a shared database was also created.
</p>

## Built with

* [Java](https://www.java.com/pl/)
* [Spring](https://spring.io/)
* [H2](https://www.h2database.com/html/main.html)
* [Lombok](https://github.com/projectlombok/lombok)
* [JavaFx](https://openjfx.io/)

## GUI
<img src = "https://github.com/jarekkopaczewski/SimpleRestApiSpringApp/blob/462c8131ef3f966058dff1f7c4aed57d154dd8d7/SchoolFinanseGUI/menu.png" width = "550"/>

## Example functions from event controller class

```java
    [...]
    
    @PostMapping("/events")
    public Event addEvent(@RequestBody Event event) { return eventService.addEvent(event); }

    /**
     * zamiana listy na strumień, a następnie zamiana Event na EventDto za pomocą map
     * @return
     */
    @GetMapping("/events")
    public List<EventDto> findAllEvents() {
        return eventService.findAllEvents()
                .stream()
                .map(EventDto::of)
                .collect(Collectors.toList());
    }
    
    [...]
}
```

## Part of payment class table

```java
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
    
    [...]
}
```

## Event table model
```java
@Getter
@Setter
public class EventTableModel {
    private final Long eventId;
    private final String name;
    private final String place;
    private final String date;

    public EventTableModel(Long eventId, String name, String place, String date) {
        this.eventId = eventId;
        this.name = name;
        this.place = place;
        this.date = date;
    }

    public static EventTableModel of(EventDto dto){
        return new EventTableModel(dto.getEventId(), dto.getName(), dto.getPlace(), dto.getDate());
    }
}
```
## Payment rest client

```java
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
```

## License

Distributed under the Apache-2.0 License.

<p align="right">(<a href="#top">back to top</a>)</p>
