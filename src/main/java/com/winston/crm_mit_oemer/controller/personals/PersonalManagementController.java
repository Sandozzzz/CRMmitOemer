package com.winston.crm_mit_oemer.controller.personals;

import com.winston.crm_mit_oemer.App;
import javafx.fxml.FXML;

import java.io.IOException;

public class PersonalManagementController {


    @FXML
    protected void onAddPersonalClicked() throws IOException {
        App.setRoot("personal-add-view");

    }

    @FXML
    protected void onBackClicked() throws IOException {
        App.setRoot("main-view");

    }
}
