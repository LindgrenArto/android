package com.example.helloworld.ContactsPackage;

public class Contact {

    public int id;
    public int phoneNumber;
    public int postalCode;
    public String firstName;

    public Contact() {

    }

    public String lastName;
    public String streetAddress;
    public String city;
    public String emailAddress;


    public Contact(int id, int phoneNumber, int postalCode, String firstName, String lastName, String streetAddress, String city, String emailAddress) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.postalCode = postalCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.emailAddress = emailAddress;
    }

}
