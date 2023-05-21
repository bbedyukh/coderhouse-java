package com.coderhouse.java.persistences.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.util.Calendar;
import java.util.Date;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nonnull
    private String firstName;

    @Nonnull
    private String lastName;

    @Nonnull
    private Calendar birthDate;

    public Client() {
    }

    public Client(String firstName, String lastName, Calendar birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public static Client createWith(String firstName, String lastName, Calendar birthDate) {
        return new Client(firstName, lastName, birthDate);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean hasFirstName() {
        return firstName != null;
    }

    public boolean hasLastName() {
        return lastName != null;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public boolean hasBirthDate() {
        return birthDate != null;
    }
}
