package pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "person")
public class Person {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long personId;
    private String name;
    private String surname;

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "paymentPerson", cascade = CascadeType.ALL)
//    private List<Payment> personPayments;

    public Person() { }

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}