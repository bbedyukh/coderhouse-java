package com.coderhouse.java.dto;

public class ClientDTO {

    public String firstName;
    public String lastName;
    public int age;

    public ClientDTO() {
    }

    public ClientDTO(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
