package com.winston.crm_mit_oemer.service;

import com.winston.crm_mit_oemer.model.Customer;
import com.winston.crm_mit_oemer.model.Note;
import com.winston.crm_mit_oemer.util.CustomerType;
import com.winston.crm_mit_oemer.util.ICRUD;
import com.winston.crm_mit_oemer.util.UserType;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class CustomerManager implements ICRUD<Customer,Note> {
    private final String TABLE_NAME = "customer";
    private final String NOTE_TABLE = "notes";

    @Override
    public boolean save(Customer customer) throws SQLException {

            final String SQL = "INSERT INTO " + TABLE_NAME + "(id, name, surname, email, status, phone, profilePhoto, createdDate, company, customerType ) VALUES (NULL,?,?,?,?,?,?,?,?,?)";

            try(Connection con = ConnectionFactory.getConnection();
                PreparedStatement stmt = con.prepareStatement(SQL)) {

                stmt.setString(1, customer.getName());
                stmt.setString(2, customer.getSurname());
                stmt.setString(3, customer.getEmail());
                stmt.setString(4, customer.getStatus().name());
                stmt.setString(5, customer.getPhone());
                stmt.setBytes(6, customer.getProfilePhoto());
                stmt.setDate(7, Date.valueOf(customer.getCreatedDate()));
                stmt.setString(8, customer.getCompany());
                stmt.setString(9,customer.getCustomerType().name());

                return stmt.executeUpdate() > 0;
            }
    }

    @Override
    public List<Customer> findAll() throws SQLException {
        List<Customer> customers = new ArrayList<Customer>();
        final String SQL = "SELECT * FROM " + TABLE_NAME;
        try(Connection con = ConnectionFactory.getConnection(); Statement stmt = con.prepareStatement(SQL)){
          ResultSet resultSet =  stmt.executeQuery(SQL);

          while (resultSet.next()) {
              customers.add(create(resultSet));
          }
        }

        return customers;
    }

    @Override
    public boolean update(Customer customer) throws SQLException {
        final String SQL = "UPDATE " + TABLE_NAME + " SET name=?, surname=?, email=?, status=?, phone=?, profilePhoto=?, createdDate=?, company=?, customerType=? WHERE id=?";
        try ( Connection con = ConnectionFactory.getConnection();
              PreparedStatement stmt = con.prepareStatement(SQL)){
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getSurname());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getStatus().name());
            stmt.setString(5, customer.getPhone());
            stmt.setBytes(6, customer.getProfilePhoto());
            stmt.setDate(7, Date.valueOf(customer.getCreatedDate()));
            stmt.setString(8, customer.getCompany());
            stmt.setString(9,customer.getCustomerType().name());
            stmt.setInt(10, customer.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(Customer customer) throws SQLException {
        final String SQL = "DELETE FROM " + TABLE_NAME + " WHERE id=?";
        try ( Connection con = ConnectionFactory.getConnection();
              PreparedStatement stmt = con.prepareStatement(SQL)){
            stmt.setInt(1, customer.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public Customer findById(int id) throws SQLException {
        final String SQL = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
        try ( Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(SQL)){
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return create(resultSet);
            }
        }
        return null;
    }

    @Override
    public Customer create(ResultSet rs) throws SQLException {
        Customer cus = new Customer();
        cus.setId(rs.getInt("id"));
        cus.setName(rs.getString("name"));
        cus.setSurname(rs.getString("surname"));
        cus.setEmail(rs.getString("email"));
        cus.setStatus(UserType.valueOf(rs.getString("status")));
        cus.setPhone(rs.getString("phone"));
        cus.setCompany(rs.getString("company"));
        cus.setCreatedDate(rs.getDate("createdDate").toLocalDate());
        cus.setProfilePhoto(rs.getBytes("profilePhoto"));
        cus.setCustomerType(CustomerType.valueOf(rs.getString("customerType")));
        return cus;
    }

    @Override
    public boolean addNote(Note note) throws SQLException {
        final String SQL = "INSERT INTO " + NOTE_TABLE + "(note_id, note, user_id, customer_id, createdDate) VALUES (NULL,?,?,?,?)";
        try ( Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(SQL)){
            stmt.setString(1, note.getNote());
            stmt.setNull(2, Types.INTEGER);
            stmt.setInt(3, note.getCustomerId());
            stmt.setDate(4, Date.valueOf(note.getCreatedDate()));
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean updateNote(Note note) throws SQLException {
        final String SQL = "UPDATE " + NOTE_TABLE + " SET note=?, createdDate=? WHERE customer_id=?";
        try ( Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(SQL)){
            stmt.setString(1, note.getNote());
            stmt.setDate(2, Date.valueOf(note.getCreatedDate()));
            stmt.setInt(3, note.getCustomerId());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public Note findNoteById(int id) throws SQLException {
        final String SQL = "SELECT * FROM " + NOTE_TABLE + " WHERE customer_id=?";
        try ( Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(SQL)){
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Note note = new Note();
                note.setId(resultSet.getInt("note_id"));
                note.setNote(resultSet.getString("note"));
                note.setUserId(resultSet.getInt("user_id"));
                note.setCustomerId(resultSet.getInt("customer_id"));
                note.setCreatedDate(resultSet.getDate("createdDate").toLocalDate());
                return note;
            }
        }
        return null;
    }
}
