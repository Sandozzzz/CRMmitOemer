package com.winston.crm_mit_oemer.controller.login;

import com.winston.crm_mit_oemer.App;
import com.winston.crm_mit_oemer.model.UserDTO;
import com.winston.crm_mit_oemer.service.LoginManager;
import com.winston.crm_mit_oemer.util.CustomErrorAlert;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField email;
    @FXML
    private PasswordField passwordTextField;


    @FXML
        protected void onLoginButtonClicked() throws IOException {
        UserDTO userDTO = new UserDTO();
       // userDTO.setEmail(email.getText());
       // userDTO.setPassword(passwordTextField.getText());
        userDTO.setEmail("t.edison@crm.de");
        userDTO.setPassword("123456");

        LoginManager loginManager = LoginManager.getInstance();
        try {
            if(loginManager.logIn(userDTO)){
                // New users have to change Password
                if(loginManager.getCurrentUser().isNewUser()){
                    App.setRoot("pwd-change-view");
                }else{
                    App.setRoot("main-view");
                }

            }else {
                CustomErrorAlert.showAlert("Falsche Eingabe! \n" +
                        "Bitte versuchen Sie es erneut.");
            }
        } catch (SQLException e) {
            CustomErrorAlert.showAlert("Ein Fehler beim Login ist aufgetreten! \n" + e.getMessage());
        }


    }
}
