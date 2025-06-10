package com.winston.crm_mit_oemer.service;

import com.winston.crm_mit_oemer.model.Customer;
import com.winston.crm_mit_oemer.util.ICRUD;

import java.sql.*;
import java.util.List;

public class CustomerManager implements ICRUD<Customer> {
    private final String TABLE_NAME = "customer";

    @Override
    public boolean save(Customer customer) throws SQLException {
        if (customer.getId() > 0) {
            //TODO: Update customer
        }
        else {
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

        return false;
    }

    @Override
    public List<Customer> findAll() throws SQLException {
        return List.of();
    }

    @Override
    public boolean update(Customer customer) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Customer customer) throws SQLException {
        return false;
    }

    @Override
    public Customer findById(int id) throws SQLException {
        return null;
    }

    @Override
    public Customer create(ResultSet rs) throws SQLException {
        return null;
    }
}
