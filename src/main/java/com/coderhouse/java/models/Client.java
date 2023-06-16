package com.coderhouse.java.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

@Entity
@Table(indexes = @Index(columnList = "dni"))
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nonnull
    @Column(length = 75)
    private String firstname;

    @Nonnull
    @Column(length = 75)
    private String lastname;

    @Nonnull
    @Column(unique = true, length = 11)
    private Integer dni;

    public Client() {
    }

    public Client(String firstname, String lastname, Integer dni) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dni = dni;
    }

    public static Client createWith(String firstname, String lastname, Integer dni) {
        return new Client(firstname, lastname, dni);
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }


    public String getLastname() {
        return lastname;
    }

    public Integer getDni() {
        return dni;
    }

    public boolean hasFirstName() {
        return firstname != null;
    }

    public boolean hasLastName() {
        return lastname != null;
    }

    public boolean hasDNI() {
        return dni != null;
    }

    public void updateWith(String firstname, String lastname, Integer dni) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dni = dni;
    }

}
