package com.winston.crm_mit_oemer.model;

import com.winston.crm_mit_oemer.util.PriorityType;
import com.winston.crm_mit_oemer.util.TaskStatus;
import com.winston.crm_mit_oemer.util.TaskType;
import java.time.LocalDate;

public class TaskDTO extends Tasks {
    private User personal;
    private Customer customer;

    public TaskDTO(int id, TaskType taskType, String description, TaskStatus taskStatus, PriorityType priorityType, int taggedPersonalId, int taggedCustomerId, LocalDate startDate, LocalDate endDate, LocalDate createdDate,User personal, Customer customer) {
        super();
        this.personal = personal;
        this.customer = customer;
    }


}
