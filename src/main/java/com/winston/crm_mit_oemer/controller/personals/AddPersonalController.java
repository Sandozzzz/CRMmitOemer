package com.winston.crm_mit_oemer.controller.personals;

import com.winston.crm_mit_oemer.App;
import javafx.fxml.FXML;

import java.io.IOException;

public class AddPersonalController {


    @FXML
    protected void onSaveCustomerClicked() throws IOException {
        System.out.println("Personal saved");
        // App.setRoot("add-task-view");

    }
    @FXML
    protected void onCancelClicked() throws IOException {
        App.setRoot("personal-management-view");

    }
}
