package com.example.hitesh.contactsqlite;

public class Contact {

    int id;
    String name;
    String phone;
    String email;

    public Contact() {
    }

    public Contact(String na, String em, String mob) {
        this.name =na;
        this.phone = mob;
        this.email = em;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

