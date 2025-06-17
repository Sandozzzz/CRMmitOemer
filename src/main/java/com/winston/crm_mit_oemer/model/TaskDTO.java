package com.winston.crm_mit_oemer.model;

import com.winston.crm_mit_oemer.util.PriorityType;
import com.winston.crm_mit_oemer.util.TaskStatus;
import com.winston.crm_mit_oemer.util.TaskType;
import java.time.LocalDate;

public class TaskDTO extends Tasks {
    private User personal;
    private Customer customer;

    public TaskDTO(int id, TaskType taskType, String description, TaskStatus taskStatus, PriorityType priorityType, int taggedPersonalId, int taggedCustomerId, LocalDate startDate, LocalDate endDate, LocalDate createdDate,User personal, Customer customer) {
        super(id, taskType, description, taskStatus, priorityType, taggedPersonalId, taggedCustomerId, startDate, endDate, createdDate);
        this.personal = personal;
        this.customer = customer;
    }
    public TaskDTO(TaskType taskType, String description, TaskStatus taskStatus, PriorityType priorityType, int taggedPersonalId, int taggedCustomerId, LocalDate startDate, LocalDate endDate, LocalDate createdDate) {
        super(taskType,description,taskStatus,priorityType,taggedPersonalId,taggedCustomerId,startDate,endDate,createdDate);

    }
    public TaskDTO() {
    }

    public User getPersonal() {
        return personal;
    }

    public void setPersonal(User personal) {
        this.personal = personal;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getPersonalName(){
        return personal.getName()+' '+personal.getSurname();
    }

    public String getCustomerName(){
        return customer.getName()+' '+customer.getSurname();
    }


    @Override
    public String toString() {
        return "TaskDTO{" +
                "personal=" + personal +
                ", customer=" + customer +
                ", id=" + id +
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
