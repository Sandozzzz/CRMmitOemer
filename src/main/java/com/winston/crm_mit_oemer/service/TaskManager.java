package com.winston.crm_mit_oemer.service;

import com.winston.crm_mit_oemer.model.Customer;
import com.winston.crm_mit_oemer.model.Tasks;
import com.winston.crm_mit_oemer.util.ICRUD;
import com.winston.crm_mit_oemer.util.PriorityType;
import com.winston.crm_mit_oemer.util.TaskStatus;
import com.winston.crm_mit_oemer.util.TaskType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManager implements ICRUD<Tasks> {
    private final String TABLE_NAME = "tasks";

    @Override
    public boolean save(Tasks task) throws SQLException {
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
    public List<Tasks> findAll() throws SQLException {
        List<Tasks> tasks = new ArrayList<>();
        final String SQL = "SELECT * FROM " + TABLE_NAME;
        try (Connection con = ConnectionFactory.getConnection(); Statement stmt = con.prepareStatement(SQL)) {
            ResultSet resultSet = stmt.executeQuery(SQL);

            while (resultSet.next()) {
                tasks.add(create(resultSet));
            }
        }

        return tasks;
    }

    @Override
    public boolean update(Tasks task) throws SQLException {
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
    public boolean delete(Tasks task) throws SQLException {
        final String SQL = "DELETE FROM " + TABLE_NAME + " WHERE id=?";
        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(SQL)) {
            stmt.setInt(1, task.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public Tasks findById(int id) throws SQLException {
        final String SQL = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(SQL)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return create(resultSet);
            }
        }
        return null;
    }

    @Override
    public Tasks create(ResultSet rs) throws SQLException {
        Tasks task = new Tasks();
        task.setId(rs.getInt("id"));
        task.setTaskType(TaskType.valueOf(rs.getString("taskType")));
        task.setDescription(rs.getString("description"));
        task.setTaskStatus(TaskStatus.valueOf(rs.getString("status")));
        task.setPriorityType(PriorityType.valueOf(rs.getString("priority")));
        task.setTaggedPersonalId(rs.getInt("personalid"));
        task.setTaggedCustomerId(rs.getInt("customerid"));
        task.setStartDate(rs.getDate("startdate").toLocalDate());
        task.setEndDate(rs.getDate("enddate").toLocalDate());
        task.setCreatedDate(rs.getDate("createdDate").toLocalDate());

        return task;
    }
}
