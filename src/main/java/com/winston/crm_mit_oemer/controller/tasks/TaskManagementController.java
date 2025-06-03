package com.winston.crm_mit_oemer.controller.tasks;

import com.winston.crm_mit_oemer.App;
import javafx.fxml.FXML;

import java.io.IOException;

public class TaskManagementController {

    @FXML
    protected void onAddTaskClicked() throws IOException {
        App.setRoot("task-add-view");

    }

    @FXML
    protected void onBackClicked() throws IOException {
        App.setRoot("main-view");

    }
}
