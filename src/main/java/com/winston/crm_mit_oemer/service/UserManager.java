package com.winston.crm_mit_oemer.service;

import com.winston.crm_mit_oemer.model.Customer;
import com.winston.crm_mit_oemer.model.Note;
import com.winston.crm_mit_oemer.model.User;
import com.winston.crm_mit_oemer.util.CustomerType;
import com.winston.crm_mit_oemer.util.ICRUD;
import com.winston.crm_mit_oemer.util.UserType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * User Manager class is used to perform CRUD functions for the users
 * **/

public class UserManager implements ICRUD<User, Note> {
    private final String TABLE_NAME = "users";
    private final String NOTE_TABLE = "notes";

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
    public List<User> findById(int id) throws SQLException {
        final String SQL = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
        List<User> users = new ArrayList<>();
        try ( Connection con = ConnectionFactory.getConnection();
              PreparedStatement stmt = con.prepareStatement(SQL)){
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                 users.add(create(resultSet));
            }
        }
        return users;
    }

    public Optional<User> findByEmail(String email) throws SQLException {
        final String SQL = "SELECT * FROM " + TABLE_NAME + " WHERE email=?";
        try ( Connection con = ConnectionFactory.getConnection();
              PreparedStatement stmt = con.prepareStatement(SQL)){
            stmt.setString(1, email);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return Optional.ofNullable(create(resultSet));
            }
        }
        return Optional.empty();
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

    @Override
    public boolean addNote(Note note) throws SQLException {
        final String SQL = "INSERT INTO " + NOTE_TABLE + "(note_id, note, user_id, customer_id, createdDate) VALUES (NULL,?,?,?,?)";
        try ( Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(SQL)){
            stmt.setString(1, note.getNote());
            stmt.setInt(2, note.getUserId());
            stmt.setNull(3, Types.INTEGER);
            stmt.setDate(4, Date.valueOf(note.getCreatedDate()));
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean updateNote(Note note) throws SQLException {
        final String SQL = "UPDATE " + NOTE_TABLE + " SET note=?, createdDate=? WHERE user_id=?";
        try ( Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(SQL)){
            stmt.setString(1, note.getNote());
            stmt.setDate(2, Date.valueOf(note.getCreatedDate()));
            stmt.setInt(3, note.getUserId());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public Note findNoteById(int id) throws SQLException {
        final String SQL = "SELECT * FROM " + NOTE_TABLE + " WHERE user_id=?";
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
