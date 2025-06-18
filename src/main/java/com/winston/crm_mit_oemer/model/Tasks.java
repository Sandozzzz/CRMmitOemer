package com.winston.crm_mit_oemer.model;

import com.winston.crm_mit_oemer.util.PriorityType;
import com.winston.crm_mit_oemer.util.TaskStatus;
import com.winston.crm_mit_oemer.util.TaskType;

import java.time.LocalDate;

/**Task model **/
public class Tasks {
    int id;
    TaskType taskType;
    String description;
    TaskStatus taskStatus;
    PriorityType priorityType;
    int taggedPersonalId;
    int taggedCustomerId;
    LocalDate startDate;
    LocalDate endDate;
    LocalDate createdDate;

    public Tasks(TaskType taskType, String description, TaskStatus taskStatus, PriorityType priorityType, int taggedPersonalId, int taggedCustomerId, LocalDate startDate, LocalDate endDate, LocalDate createdDate) {
        this.taskType = taskType;
        this.description = description;
        this.taskStatus = taskStatus;
        this.priorityType = priorityType;
        this.taggedPersonalId = taggedPersonalId;
        this.taggedCustomerId = taggedCustomerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdDate = createdDate;
    }
    public Tasks() {
    }

    public Tasks(int id, TaskType taskType, String description, TaskStatus taskStatus, PriorityType priorityType, int taggedPersonalId, int taggedCustomerId, LocalDate startDate, LocalDate endDate, LocalDate createdDate) {
        this(taskType,description,taskStatus,priorityType,taggedPersonalId,taggedCustomerId,startDate,endDate,createdDate);
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public PriorityType getPriorityType() {
        return priorityType;
    }

    public void setPriorityType(PriorityType priorityType) {
        this.priorityType = priorityType;
    }

    public int getTaggedPersonalId() {
        return taggedPersonalId;
    }

    public void setTaggedPersonalId(int taggedPersonalId) {
        this.taggedPersonalId = taggedPersonalId;
    }

    public int getTaggedCustomerId() {
        return taggedCustomerId;
    }

    public void setTaggedCustomerId(int taggedCustomerId) {
        this.taggedCustomerId = taggedCustomerId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Tasks{" +
                "id=" + id +
                ", taskType=" + taskType +
                ", description='" + description + '\'' +
                ", taskStatus=" + taskStatus +
                ", priorityType=" + priorityType +
                ", taggedPersonalId=" + taggedPersonalId +
                ", taggedCustomerId=" + taggedCustomerId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", createdDate=" + createdDate +
                '}';
    }
}
