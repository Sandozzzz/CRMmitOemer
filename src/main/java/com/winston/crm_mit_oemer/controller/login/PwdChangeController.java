package com.winston.crm_mit_oemer.controller.login;

import com.winston.crm_mit_oemer.App;
import com.winston.crm_mit_oemer.model.User;
import com.winston.crm_mit_oemer.service.LoginManager;
import com.winston.crm_mit_oemer.util.CustomErrorAlert;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.util.Duration;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;

public class PwdChangeController {

    @FXML
    private PasswordField passwordTextField;
    @FXML
    private PasswordField passwordTextField2;
    @FXML
    private Label infoLabel;

    LoginManager loginManager = LoginManager.getInstance();


    @FXML
    protected void onAffirmButtonClicked() {
        if (!passwordTextField.getText().equals(passwordTextField2.getText())) {
            setInfoLabel("Die Passwörte nicht übereinstimmen!");
            return;
        }

        User user = loginManager.getCurrentUser();
        String pswd = BCrypt.hashpw(passwordTextField.getText(), BCrypt.gensalt());
        user.setPassword(pswd);
        user.setNewUser(false);
        try {
            if (loginManager.updatePassword()) {
                loginManager.logOut();

                setInfoLabel("Ihr Passwort wurde erfolgreich geändert.\nSie werden umgeleitet...");

                // Create a PauseTransition for 2 seconds
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(event -> {
                    // This code will be executed after 2 seconds
                    try {
                        App.setRoot("login-view");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                pause.play(); // Start the timer
            } else {
                CustomErrorAlert.showAlert("Ein Fehler ist aufgetreten! \n" +
                        "Bitte versuchen Sie es erneut.");
            }

        } catch (Exception e) {
            CustomErrorAlert.showAlert("Ein Fehler ist aufgetreten! \n" + e.getMessage());
            e.printStackTrace();
        }
    }

    private void setInfoLabel(String info){
        infoLabel.setVisible(true);
        infoLabel.setText(info);
    }

}
