package com.winston.crm_mit_oemer.service;

import com.winston.crm_mit_oemer.model.*;
import com.winston.crm_mit_oemer.util.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Task Manager class is used to perform CRUD functions for the tasks
 * **/
public class TaskManager implements ICRUD<TaskDTO, Note> {
    private final String TABLE_NAME = "tasks";

    @Override
    public boolean save(TaskDTO task) throws SQLException {
        final String SQL = "INSERT INTO " + TABLE_NAME + "(id, taskType, description, status, priority, personalid, customerid, startdate, enddate, createdDate) VALUES (NULL,?,?,?,?,?,?,?,?,?)";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(SQL)) {

            stmt.setString(1, task.getTaskType().name());
            stmt.setString(2, task.getDescription());
            stmt.setString(3, task.getTaskStatus().name());
            stmt.setString(4, task.getPriorityType().name());
            stmt.setInt(5, task.getTaggedPersonalId());
            stmt.setInt(6, task.getTaggedCustomerId());
            stmt.setDate(7, Date.valueOf(task.getStartDate()));
            stmt.setDate(8, Date.valueOf(task.getEndDate()));
            stmt.setDate(9, Date.valueOf(task.getCreatedDate()));

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public List<TaskDTO> findAll() throws SQLException {
        List<TaskDTO> tasks = new ArrayList<>();
        final String SQL = "SELECT t.id AS task_id, t.taskType, t.description, t.status, t.priority, t.personalid, t.customerid, t.startdate, t.enddate, t.createdDate, " +
                "p.id AS personal_id, p.name AS personal_name, p.surname AS personal_surname, p.email AS personal_email, p.password, p.status AS personal_status, p.phone AS personal_phone, p.profilePhoto AS personal_profilePhoto, p.createdDate AS personal_createdDate, p.isNewUser, " +
                "c.id AS customer_id, c.name AS customer_name, c.surname AS customer_surname, c.email AS customer_email, c.status AS customer_status, c.phone AS customer_phone, c.profilePhoto AS customer_profilePhoto, c.createdDate AS customer_createdDate, c.company, c.customerType " +
                "FROM " + TABLE_NAME + " t" +
                " JOIN users p ON t.personalid = p.id " +
                " JOIN customer c ON t.customerid = c.id";

        try (Connection con = ConnectionFactory.getConnection(); Statement stmt = con.prepareStatement(SQL)) {
            ResultSet resultSet = stmt.executeQuery(SQL);

            while (resultSet.next()) {
                tasks.add(create(resultSet));
            }
        }

        return tasks;
    }



    @Override
    public boolean update(TaskDTO task) throws SQLException {
        final String SQL = "UPDATE " + TABLE_NAME + " SET taskType=?, description=?, status=?, priority=?, personalid=?, customerid=?, startdate=?, enddate=?, createdDate=? WHERE id=?";
        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(SQL)) {
            stmt.setString(1, task.getTaskType().name());
            stmt.setString(2, task.getDescription());
            stmt.setString(3, task.getTaskStatus().name());
            stmt.setString(4, task.getPriorityType().name());
            stmt.setInt(5, task.getTaggedPersonalId());
            stmt.setInt(6, task.getTaggedCustomerId());
            stmt.setDate(7, Date.valueOf(task.getStartDate()));
            stmt.setDate(8, Date.valueOf(task.getEndDate()));
            stmt.setDate(9, Date.valueOf(task.getCreatedDate()));
            stmt.setInt(10, task.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(TaskDTO task) throws SQLException {
        final String SQL = "DELETE FROM " + TABLE_NAME + " WHERE id=?";
        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(SQL)) {
            stmt.setInt(1, task.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public List<TaskDTO> findById(int id) throws SQLException {
        List<TaskDTO> taskList = new ArrayList<>();
        final String SQL = "SELECT t.id AS task_id, t.taskType, t.description, t.status, t.priority, t.personalid, t.customerid, t.startdate, t.enddate, t.createdDate, " +
                "p.id AS personal_id, p.name AS personal_name, p.surname AS personal_surname, p.email AS personal_email, p.password, p.status AS personal_status, p.phone AS personal_phone, p.profilePhoto AS personal_profilePhoto, p.createdDate AS personal_createdDate, p.isNewUser, " +
                "c.id AS customer_id, c.name AS customer_name, c.surname AS customer_surname, c.email AS customer_email, c.status AS customer_status, c.phone AS customer_phone, c.profilePhoto AS customer_profilePhoto, c.createdDate AS customer_createdDate, c.company, c.customerType " +
                "FROM " + TABLE_NAME + " t" +
                " JOIN users p ON t.personalid = p.id " +
                " JOIN customer c ON t.customerid = c.id"+ " WHERE personalid=?";
        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(SQL)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                 taskList.add(create(resultSet));
            }
            return taskList;
        }
    }

    @Override
    public TaskDTO create(ResultSet rs) throws SQLException {
        User personal = new User();
        personal.setId(rs.getInt("personal_id"));
        personal.setName(rs.getString("personal_name"));
        personal.setSurname(rs.getString("personal_surname"));
        personal.setEmail(rs.getString("personal_email"));
        personal.setPassword(rs.getString("password"));
        personal.setStatus(UserType.valueOf(rs.getString("personal_status")));
        personal.setPhone(rs.getString("personal_phone"));
        personal.setProfilePhoto(rs.getBytes("personal_profilePhoto"));
        personal.setCreatedDate(rs.getDate("personal_createdDate").toLocalDate());
        personal.setNewUser(rs.getBoolean("isNewUser"));

        Customer customer = new Customer();
        customer.setId(rs.getInt("customer_id"));
        customer.setName(rs.getString("customer_name"));
        customer.setSurname(rs.getString("customer_surname"));
        customer.setEmail(rs.getString("customer_email"));
        customer.setStatus(UserType.valueOf(rs.getString("customer_status")));
        customer.setPhone(rs.getString("customer_phone"));
        customer.setProfilePhoto(rs.getBytes("customer_profilePhoto"));
        customer.setCreatedDate(rs.getDate("customer_createdDate").toLocalDate());
        customer.setCompany(rs.getString("company"));
        customer.setCustomerType(CustomerType.valueOf(rs.getString("customerType")));

        TaskDTO task = new TaskDTO();
        task.setId(rs.getInt("task_id"));
        task.setTaskType(TaskType.valueOf(rs.getString("taskType")));
        task.setDescription(rs.getString("description"));
        task.setTaskStatus(TaskStatus.valueOf(rs.getString("status")));
        task.setPriorityType(PriorityType.valueOf(rs.getString("priority")));
        task.setTaggedPersonalId(rs.getInt("personalid"));
        task.setTaggedCustomerId(rs.getInt("customerid"));
        task.setStartDate(rs.getDate("startdate").toLocalDate());
        task.setEndDate(rs.getDate("enddate").toLocalDate());
        task.setCreatedDate(rs.getDate("createdDate").toLocalDate());
        task.setPersonal(personal);
        task.setCustomer(customer);
        return task;
    }

    @Override
    public boolean addNote(Note note) throws SQLException {
        return false;
    }

    @Override
    public boolean updateNote(Note note) throws SQLException {
        return false;
    }
    @Override
    public Note findNoteById(int id) throws SQLException {
        return null;
    }
}
