package pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.model.Event;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.model.Payment;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.model.Person;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.service.EventService;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.service.PaymentService;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.service.PersonService;

@SpringBootApplication
public class SchoolFinanseAppApplication implements CommandLineRunner {

    private final EventService eventService;
    private final PaymentService paymentService;
    private final PersonService personService;

    @Autowired
    public SchoolFinanseAppApplication(EventService eventService, PaymentService paymentService, PersonService personService) {
        this.eventService = eventService;
        this.paymentService = paymentService;
        this.personService = personService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SchoolFinanseAppApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Event event = new Event("Wycieczka", "Wrocław", "12.12.2023");
        eventService.addEvent(event);
        eventService.addEvent(new Event("Wyjście do muzeum", "Kraków", "10.09.2023"));
        eventService.addEvent(new Event("Wyjazd na basen", "Wrocław", "08.02.2024"));

        Person person = new Person("Jan", "Nowak");
        personService.addPerson(person);
        personService.addPerson(new Person("Karol", "Gieniec"));
        personService.addPerson(new Person("Klaudia", "Zdęba"));
        personService.addPerson(new Person("Kuba", "Grzyb"));

        paymentService.addPayment(new Payment("01.11.2023", 100, 1, event, person));
        paymentService.addPayment(new Payment("15.11.2023", 100, 2, event, person));
        paymentService.addPayment(new Payment("01.12.2023", 100, 3, event, person));
    }
}