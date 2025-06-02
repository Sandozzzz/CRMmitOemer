package com.winston.crm_mit_oemer.controller;

import com.winston.crm_mit_oemer.App;
import javafx.fxml.FXML;

import java.io.IOException;

public class AddCustomerController {



    @FXML
    protected void onSaveCustomerClicked() throws IOException {
        System.out.println("Customer saved");
        // App.setRoot("add-task-view");

    }
    @FXML
    protected void onCancelClicked() throws IOException {
        App.setRoot("customer-management-view");

    }
}
