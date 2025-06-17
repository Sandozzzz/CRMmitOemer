package com.winston.crm_mit_oemer.controller.tasks;


import com.winston.crm_mit_oemer.App;
import com.winston.crm_mit_oemer.model.TaskDTO;
import com.winston.crm_mit_oemer.service.ImageHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TaskDetailController implements Initializable {
    @FXML
    private Label name;
    @FXML
    private Label email;
    @FXML
    private Label phone;
    @FXML
    private Label company;
    @FXML
    private ImageView profilePhoto;
    @FXML
    private Label taskType;
    @FXML
    private Label status;
    @FXML
    private Label priority;
    @FXML
    private Label perName;
    @FXML
    private Label perEmail;
    @FXML
    private Label startDate;
    @FXML
    private Label endDate;
    @FXML
    private Label remainingDaysLabel;
    @FXML
    private TextArea description;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Button editButton;

    TaskDTO task;

    public void getTask(TaskDTO task) {
        this.task = task;
        updateView();
    }

    private void updateView() {
        name.setText(task.getCustomerName());
        email.setText(task.getCustomer().getEmail());
        phone.setText(task.getCustomer().getPhone());
        company.setText(task.getCustomer().getCompany());
        taskType.setText(task.getTaskType().name());
        priority.setText(task.getPriorityType().name());
        status.setText(task.getTaskStatus().name());
        startDate.setText(task.getStartDate().toString());
        endDate.setText(task.getEndDate().toString());

        profilePhoto.setImage(ImageHelper.changeToImage(task.getCustomer().getProfilePhoto()));
        profilePhoto.setPreserveRatio(false);
        Circle clip = new Circle(75, 75, 75);
        profilePhoto.setClip(clip);

        int totalDays = task.getEndDate().compareTo(task.getStartDate());
        int remainingDays = task.getEndDate().compareTo(LocalDate.now());
        double percentage = (double) remainingDays / totalDays;

        System.out.println(totalDays +" "+remainingDays + " "+percentage);
        if (remainingDays < 0) {
            remainingDaysLabel.setText("0");
        } else {
            remainingDaysLabel.setText(remainingDays + " Tage übrig...");
        }

        if(percentage >1.0){
            progressBar.setProgress(0.0);
            remainingDaysLabel.setText("Noch nicht gestartet...");
        }
        else {
            progressBar.setProgress(1-percentage);
            progressBar.setStyle(changeProgressBarColor(percentage));
        }

        if (task.getPersonalName() != null) {
            perName.setText(task.getPersonalName());
            perEmail.setText(task.getPersonal().getEmail());
        }
        if (task.getDescription() != null) {
            description.setText(task.getDescription());
        } else {
            description.setText("");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    protected void onCancelClicked() throws IOException {
        App.setRoot("task-management-view");

    }

    @FXML
    protected void onEditButtonClicked() throws IOException {
      FXMLLoader  loader = new FXMLLoader(getClass().getResource("/com/winston/crm_mit_oemer/views/task-add-view.fxml"));
       Parent root = loader.load();
        AddTaskController controller = loader.getController();
        controller.getTask(task);

        Scene currentScene = editButton.getScene();
        currentScene.setRoot(root);
    }

    private String changeProgressBarColor(double percentage){
        if(percentage<0.33){
            return "-fx-accent: #ff0000;";
        }else if(percentage<0.66){
            return "-fx-accent: #ff8c00;";
        }else{
            return "-fx-accent: #00ff00;";
        }
    }
}
