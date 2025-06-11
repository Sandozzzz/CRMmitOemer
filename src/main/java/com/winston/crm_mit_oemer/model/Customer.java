package com.winston.crm_mit_oemer.model;

import com.winston.crm_mit_oemer.util.CustomerType;
import com.winston.crm_mit_oemer.util.UserType;

import java.time.LocalDate;

public class Customer extends Person {

    private String company;
    private CustomerType customerType;

    public Customer() {
        super();
    }
    public Customer(int id, String name, String surname, String email, UserType status, String phone, byte[] profilePhoto, LocalDate createdDate, String company, CustomerType customerType) {
        super(id, name, surname, email, status, phone, profilePhoto, createdDate);
        this.company = company;
        this.customerType = customerType;
    }

    public Customer(String name, String surname, String email, UserType status, String phone, byte[] profilePhoto, LocalDate createdDate, String company, CustomerType customerType) {
        super(name, surname, email, status, phone, profilePhoto, createdDate);
        this.company = company;
        this.customerType = customerType;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    @Override
    public String toString() {
        return super.toString() + " Company: " + company;
    }

}