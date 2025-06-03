package com.winston.crm_mit_oemer.controller;

import com.winston.crm_mit_oemer.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    @FXML
    private Label welcomeText;

    @FXML
    private Button personalButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO: if user is standard user, make button invisible
        //personalButton.setVisible(false);
    }
    @FXML
    protected void onKundeManagementClicked() throws IOException {
        App.setRoot("customer-management-view");

    }
    @FXML
    protected void onTaskManagementClicked() throws IOException {
        App.setRoot("task-management-view");

    }

    @FXML
    protected void onPersonalManagementClicked() throws IOException {
        App.setRoot("personal-management-view");

    }


    @FXML
    protected void onBackClicked() throws IOException {
        App.setRoot("main-view");

    }
}