package com.winston.crm_mit_oemer.model;

import com.winston.crm_mit_oemer.util.UserType;

import java.time.LocalDate;

/**Person model is a superclass. User and Customer classes are subclasses of it. **/
public class Person {
    private int id;
    private String name;
    private String surname;
    private String email;
    private UserType status;
    private String phone;
    private byte[] profilePhoto;
    private LocalDate createdDate;

    public Person(int id, String name, String surname, String email, UserType status, String phone, byte[] profilePhoto, LocalDate createdDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.status = status;
        this.phone = phone;
        this.profilePhoto = profilePhoto;
        this.createdDate = createdDate;
    }

    public Person(String name, String surname, String email, UserType status, String phone, byte[] profilePhoto, LocalDate createdDate) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.status = status;
        this.phone = phone;
        this.profilePhoto = profilePhoto;
        this.createdDate = createdDate;
    }

    public Person() {
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getStatus() {
        return status;
    }

    public void setStatus(UserType status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte[] getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(byte[] profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Person = " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                ", createdDate=" + createdDate;
    }
}
