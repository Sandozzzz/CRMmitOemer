package com.winston.crm_mit_oemer.model;

import com.winston.crm_mit_oemer.util.UserType;

import java.time.LocalDate;
/**User model **/
public class User extends Person {
private String password;
private  boolean isNewUser;

public User() {
        super();
    }

    public User(int id, String name, String surname, String email, String password, UserType status, String phone, byte[] profilePhoto, LocalDate createdDate, boolean isNewUser) {
        super(id, name, surname, email, status, phone, profilePhoto, createdDate);
        this.password = password;
        this.isNewUser = isNewUser;

    }

    public User(String name, String surname, String email, String password, UserType status, String phone, byte[] profilePhoto, LocalDate createdDate) {
        super(name, surname, email, status, phone, profilePhoto, createdDate);
        this.password = password;
        this.isNewUser = true;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isNewUser() {
        return isNewUser;
    }

    public void setNewUser(boolean newUser) {
        isNewUser = newUser;
    }

    @Override
    public String toString() {
        return super.toString() + " isNewUser= "+ this.isNewUser;
    }
}
