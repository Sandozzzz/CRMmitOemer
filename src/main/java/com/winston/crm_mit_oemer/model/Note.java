package com.winston.crm_mit_oemer.model;

import java.time.LocalDate;

/**Note model **/
public class Note {
private int id;
private String note;
private int customerId;
private int userId;
private LocalDate createdDate;

    public Note() {
    }

    public Note(int id, String note, int customerId, int userId, LocalDate createdDate) {
        this.id = id;
        this.note = note;
        this.customerId = customerId;
        this.userId = userId;
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
