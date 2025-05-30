package com.winston.crm_mit_oemer.controller;

import com.winston.crm_mit_oemer.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField email;
    @FXML
    private TextField password;

    @FXML
        protected void onLoginButtonClicked() throws IOException {
        App.setRoot("main-view");

    }
}
