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
    private String firstName;

    @Nonnull
    @Column(length = 75)
    private String lastName;

    @Nonnull
    @Column(unique = true, length = 11)
    private Integer dni;

    public Client() {
    }

    public Client(String firstName, String lastName, Integer dni) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
    }

    public static Client createWith(String firstName, String lastName, Integer dni) {
        return new Client(firstName, lastName, dni);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public Integer getDni() {
        return dni;
    }

    public boolean hasFirstName() {
        return firstName != null;
    }

    public boolean hasLastName() {
        return lastName != null;
    }

    public boolean hasDNI() {
        return dni != null;
    }

    public void updateWith(String firstName, String lastName, Integer dni) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
    }

}
