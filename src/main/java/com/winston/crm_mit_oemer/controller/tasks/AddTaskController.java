package com.winston.crm_mit_oemer.controller.tasks;

import com.winston.crm_mit_oemer.App;
import com.winston.crm_mit_oemer.model.Customer;
import com.winston.crm_mit_oemer.model.TaskDTO;
import com.winston.crm_mit_oemer.model.Tasks;
import com.winston.crm_mit_oemer.model.User;
import com.winston.crm_mit_oemer.service.CustomerManager;
import com.winston.crm_mit_oemer.service.ImageHelper;
import com.winston.crm_mit_oemer.service.TaskManager;
import com.winston.crm_mit_oemer.service.UserManager;
import com.winston.crm_mit_oemer.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class AddTaskController implements Initializable {
    @FXML
    MenuButton priorityMenu;
    @FXML
    MenuButton taskTypeMenu;
    @FXML
    MenuButton taskStatusMenu;
    @FXML
    MenuButton customerMenu;
    @FXML
    MenuButton personalMenu;
    @FXML
    DatePicker startDate;
    @FXML
    DatePicker endDate;
    @FXML
    TextArea description;

    boolean isEditMode = false;

    TaskDTO task;
    TaskType taskType;
    TaskStatus taskStatus;
    PriorityType priorityType;
    User personal;
    Customer customer;
    List<User> personals;
    List<Customer> customers;

    UserManager userManager = new UserManager();
    CustomerManager customerManager = new CustomerManager();
    TaskManager taskManager = new TaskManager();


    public void getTask(TaskDTO task) {
        this.task = task;
        updateView();
        isEditMode = true;
    }

    private void updateView() {

        taskTypeMenu.setText(task.getTaskType().name());
        priorityMenu.setText(task.getPriorityType().name());
        taskStatusMenu.setText(task.getTaskStatus().name());
        startDate.setValue(task.getStartDate());
        endDate.setValue(task.getEndDate());
        personalMenu.setText(task.getPersonalName());
        customerMenu.setText(task.getCustomerName());
        description.setText(task.getDescription());

        this.taskType = task.getTaskType();
        this.taskStatus = task.getTaskStatus();
        this.priorityType = task.getPriorityType();
        this.personal = task.getPersonal();
        this.customer = task.getCustomer();

    }

        @FXML
    protected void onSaveTaskClicked() throws IOException {
        if (startDate.getValue() == null || endDate.getValue() == null || taskType == null || taskStatus == null || priorityType == null || personal == null || customer == null) {
            CustomErrorAlert.showAlert("Füllen Sie bitte alle Feldern aus!");
            return;
        }


            try {
                boolean result;
                if (!isEditMode) {
                    LocalDate startDateValue = startDate.getValue();
                    LocalDate endDateValue = endDate.getValue();
                    task = new TaskDTO(taskType, description.getText(), taskStatus, priorityType, personal.getId(), customer.getId(), startDateValue, endDateValue, LocalDate.now());
                    result = taskManager.save(task);
                }else {
                    //update task
                    task.setTaskType(taskType);
                    task.setDescription(description.getText());
                    task.setStartDate(startDate.getValue());
                    task.setEndDate(endDate.getValue());
                    task.setPersonal(personal);
                    task.setCustomer(customer);
                    task.setPriorityType(priorityType);
                    task.setTaskStatus(taskStatus);

                    result = taskManager.update(task);
                }

                if(result){
               App.setRoot("task-management-view");
           }

        } catch (SQLException e) {
            CustomErrorAlert.showAlert("Fehler beim Speichern des Tasks! \n" + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Tasks saved" + task);


    }

    @FXML
    protected void onCancelClicked() throws IOException {
        App.setRoot("task-management-view");

    }

    @FXML
    protected void onStatusMenuItemClicked(ActionEvent event) {
        MenuItem clickedItem = (MenuItem) event.getSource();
        String itemText = clickedItem.getText();
        taskStatusMenu.setText(itemText);
        taskStatus = TaskStatus.valueOf(itemText);
    }

    @FXML
    protected void onPriorityMenuItemClicked(ActionEvent event) {
        MenuItem clickedItem = (MenuItem) event.getSource();
        String itemText = clickedItem.getText();
        priorityMenu.setText(itemText);
        priorityType = PriorityType.valueOf(itemText);
    }

    @FXML
    protected void onTaskTypeMenuItemClicked(ActionEvent event) {
        MenuItem clickedItem = (MenuItem) event.getSource();
        String itemText = clickedItem.getText();
        taskTypeMenu.setText(itemText);
        taskType = TaskType.valueOf(itemText);
    }

    @FXML
    protected void onPersonalMenuItemClicked(ActionEvent event) {
        MenuItem clickedItem = (MenuItem) event.getSource();
        String itemText = clickedItem.getText();
        personalMenu.setText(itemText);
        //find user
        personal = personals.stream().filter(personal -> personal.getId() == parseInt(itemText.split("\\(id: ")[1].replace(")", ""))).findFirst().orElse(null);
    }

    @FXML
    protected void onCustomerMenuItemClicked(ActionEvent event) {
        MenuItem clickedItem = (MenuItem) event.getSource();
        String itemText = clickedItem.getText();
        customerMenu.setText(itemText);
        // find customer
        customer = customers.stream().filter(customer -> customer.getId() == parseInt(itemText.split("\\(id: ")[1].replace(")", "")
        )).findFirst().orElse(null);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            personals = userManager.findAll();
            customers = customerManager.findAll();
            for (User personal : personals) {
                MenuItem personalMenuItem = new MenuItem(personal.getName() + " " + personal.getSurname() + " (id: " + personal.getId() + ")");
                personalMenuItem.setOnAction(this::onPersonalMenuItemClicked);
                personalMenu.getItems().add(personalMenuItem);

            }
            for (Customer customer : customers) {
                MenuItem customerMenuItem = new MenuItem(customer.getName() + " " + customer.getSurname() + " (id: " + customer.getId() + ")");
                customerMenuItem.setOnAction(this::onCustomerMenuItemClicked);
                customerMenu.getItems().add(customerMenuItem);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
