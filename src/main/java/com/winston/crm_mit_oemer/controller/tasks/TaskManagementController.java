package com.winston.crm_mit_oemer.controller.tasks;

import com.winston.crm_mit_oemer.App;
import com.winston.crm_mit_oemer.controller.DetailViewController;
import com.winston.crm_mit_oemer.model.TaskDTO;
import com.winston.crm_mit_oemer.service.TaskManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class TaskManagementController implements Initializable {
TaskManager taskManager = new TaskManager();

@FXML
private TableView<TaskDTO> taskTableView;

    @FXML
    protected void onAddTaskClicked() throws IOException {
        App.setRoot("task-add-view");

    }

    @FXML
    protected void onBackClicked() throws IOException {
        App.setRoot("main-view");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            List<TaskDTO> taskList = taskManager.findAll();
            System.out.println(taskList);
            TableColumn<TaskDTO,String> personalColumn = new TableColumn<>("Personal");
            personalColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPersonalName()));
            TableColumn<TaskDTO,String> customerColumn = new TableColumn<>("Kunde");
            customerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCustomerName()));
            taskTableView.getColumns().addAll(personalColumn,customerColumn);
            taskTableView.setItems(FXCollections.observableList(taskList));
            taskTableView.getSelectionModel().selectedItemProperty().addListener((observable, taskDTO, newValue) -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/winston/crm_mit_oemer/views/task-detail-view.fxml"));
                    Parent root = loader.load();
                    TaskDetailController controller = loader.getController();
                    controller.getTask(newValue);
                    Scene currentScene = taskTableView.getScene();
                    currentScene.setRoot(root);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
