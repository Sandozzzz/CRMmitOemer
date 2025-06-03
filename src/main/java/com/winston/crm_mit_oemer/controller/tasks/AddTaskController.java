package com.winston.crm_mit_oemer.controller.tasks;

import com.winston.crm_mit_oemer.App;
import javafx.fxml.FXML;

import java.io.IOException;

public class AddTaskController {



    @FXML
    protected void onSaveTaskClicked() throws IOException {
        System.out.println("Task saved");
       // App.setRoot("add-task-view");

    }
    @FXML
    protected void onCancelClicked() throws IOException {
        App.setRoot("task-management-view");

    }
}
