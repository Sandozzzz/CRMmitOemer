package com.winston.crm_mit_oemer.controller.customers;

import com.winston.crm_mit_oemer.App;
import javafx.fxml.FXML;

import java.io.IOException;

public class CustomerManagementController {


    @FXML
    protected void onAddCustomerClicked() throws IOException {
        App.setRoot("customer-add-view");

    }

    @FXML
    protected void onBackClicked() throws IOException {
        App.setRoot("main-view");

    }
}
