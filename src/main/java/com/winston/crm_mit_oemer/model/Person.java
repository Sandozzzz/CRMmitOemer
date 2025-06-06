package com.winston.crm_mit_oemer.model;

import com.winston.crm_mit_oemer.util.StatusType;

import java.time.LocalDate;
import java.util.Arrays;

public class Person {
    private int id;
    private String name;
    private String surname;
    private String email;
    private StatusType  status;
    private String phone;
    private byte[] profilePhoto;
    private LocalDate createdDate;

    public Person(int id, String name, String surname, String email, StatusType status, String phone, byte[] profilePhoto, LocalDate createdDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.status = status;
        this.phone = phone;
        this.profilePhoto = profilePhoto;
        this.createdDate = createdDate;
    }

    public Person(String name, String surname, String email, StatusType status, String phone, byte[] profilePhoto, LocalDate createdDate) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.status = status;
        this.phone = phone;
        this.profilePhoto = profilePhoto;
        this.createdDate = createdDate;
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

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
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
                ", profilePhoto=" + Arrays.toString(profilePhoto) +
                ", createdDate=" + createdDate;
    }
}
