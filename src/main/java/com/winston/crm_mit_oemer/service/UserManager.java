package com.winston.crm_mit_oemer.service;

import com.winston.crm_mit_oemer.model.Customer;
import com.winston.crm_mit_oemer.model.User;
import com.winston.crm_mit_oemer.util.CustomerType;
import com.winston.crm_mit_oemer.util.ICRUD;
import com.winston.crm_mit_oemer.util.UserType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager implements ICRUD<User> {
    private final String TABLE_NAME = "users";

    @Override
    public boolean save(User user) throws SQLException {

            final String SQL = "INSERT INTO " + TABLE_NAME + "(id, name, surname, email, password, status, phone, profilePhoto, createdDate, isNewUser) VALUES (NULL,?,?,?,?,?,?,?,?,?)";

            try(Connection con = ConnectionFactory.getConnection();
                PreparedStatement stmt = con.prepareStatement(SQL)) {

                stmt.setString(1, user.getName());
                stmt.setString(2, user.getSurname());
                stmt.setString(3, user.getEmail());
                stmt.setString(4, user.getPassword());
                stmt.setString(5, user.getStatus().name());
                stmt.setString(6, user.getPhone());
                stmt.setBytes(7, user.getProfilePhoto());
                stmt.setDate(8, Date.valueOf(user.getCreatedDate()));
                stmt.setBoolean(9, user.isNewUser());


                return stmt.executeUpdate() > 0;
            }

    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        final String SQL = "SELECT * FROM " + TABLE_NAME;
        try(Connection con = ConnectionFactory.getConnection(); Statement stmt = con.prepareStatement(SQL)){
            ResultSet resultSet =  stmt.executeQuery(SQL);

            while (resultSet.next()) {
                users.add(create(resultSet));
            }
        }

        return users;
    }

    @Override
    public boolean update(User user) throws SQLException {
        final String SQL = "UPDATE " + TABLE_NAME + " SET name=?, surname=?, email=?, password=?, status=?, phone=?, profilePhoto=?, createdDate=?, isNewUser=? WHERE id=?";
        try ( Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(SQL)){
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getStatus().name());
            stmt.setString(6, user.getPhone());
            stmt.setBytes(7, user.getProfilePhoto());
            stmt.setDate(8, Date.valueOf(user.getCreatedDate()));
            stmt.setBoolean(9, user.isNewUser());
            stmt.setInt(10, user.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(User user) throws SQLException {
        final String SQL = "DELETE FROM " + TABLE_NAME + " WHERE id=?";
        try ( Connection con = ConnectionFactory.getConnection();
              PreparedStatement stmt = con.prepareStatement(SQL)){
            stmt.setInt(1, user.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public User findById(int id) throws SQLException {
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
    public User create(ResultSet rs) throws SQLException {
       User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setSurname(rs.getString("surname"));
        user.setEmail(rs.getString("email"));
        user.setStatus(UserType.valueOf(rs.getString("status")));
        user.setPhone(rs.getString("phone"));
        user.setPassword(rs.getString("password"));
        user.setCreatedDate(rs.getDate("createdDate").toLocalDate());
        user.setProfilePhoto(rs.getBytes("profilePhoto"));
        user.setNewUser(rs.getBoolean("isNewUser"));
        return user;
    }
}
