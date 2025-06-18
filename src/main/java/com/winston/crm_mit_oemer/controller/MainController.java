package com.winston.crm_mit_oemer.controller;

import com.winston.crm_mit_oemer.App;
import com.winston.crm_mit_oemer.model.User;
import com.winston.crm_mit_oemer.service.LoginManager;
import com.winston.crm_mit_oemer.util.UserType;
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

    User user = LoginManager.getInstance().getCurrentUser();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (user == null) {
            try {
                App.setRoot("login-view");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        welcomeText.setText("Willkommen, " + user.getName() +"!");
        //if the user is PERSONAL, make the button disabled
        if(user.getStatus() == UserType.PERSONAL) {
            personalButton.setDisable(true);
        }
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