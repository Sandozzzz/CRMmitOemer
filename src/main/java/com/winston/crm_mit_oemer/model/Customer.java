package com.winston.crm_mit_oemer.model;

import com.winston.crm_mit_oemer.util.StatusType;

import java.time.LocalDate;

public class Customer extends Person{

  private  String company;

    public Customer(int id, String name, String surname, String email, StatusType status, String phone, byte[] profilePhoto, LocalDate createdDate, String company) {
        super(id, name, surname, email, status, phone, profilePhoto, createdDate);
        this.company = company;
    }

    public Customer(String name, String surname, String email, StatusType status, String phone, byte[] profilePhoto, LocalDate createdDate, String company) {
        super(name, surname, email, status, phone, profilePhoto, createdDate);
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return super.toString() + " Company: " + company;
    }
}
