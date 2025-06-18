package com.winston.crm_mit_oemer.util;

import javafx.scene.control.Alert;

/** to display alert when an error is thrown. **/
public class CustomErrorAlert {


    public static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }
}
