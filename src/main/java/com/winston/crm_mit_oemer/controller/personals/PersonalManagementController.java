package com.winston.crm_mit_oemer.controller.personals;

import com.winston.crm_mit_oemer.App;
import com.winston.crm_mit_oemer.controller.DetailViewController;
import com.winston.crm_mit_oemer.model.Customer;
import com.winston.crm_mit_oemer.model.Person;
import com.winston.crm_mit_oemer.model.User;
import com.winston.crm_mit_oemer.service.UserManager;
import com.winston.crm_mit_oemer.util.CustomErrorAlert;
import com.winston.crm_mit_oemer.util.CustomListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
/**
 * Personal Management Controller is used to show the users.
 * It guides you to add and edit any personals **/
public class PersonalManagementController implements Initializable {

    @FXML
    private ListView<Person> personalListView;


    UserManager userManager = new UserManager();
    ObservableList<Person> observableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            List<User> personals = userManager.findAll();
            System.out.println(personals);
            observableList = FXCollections.observableArrayList(personals);
            personalListView.setItems(observableList);

            personalListView.setCellFactory(param -> new CustomListCell());
            personalListView.getSelectionModel().selectedItemProperty().addListener((observable, person, newValue) -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/winston/crm_mit_oemer/views/detail-view.fxml"));
                    Parent root = loader.load();
                    DetailViewController controller = loader.getController();
                    controller.setPerson(newValue);
                    Scene currentScene = personalListView.getScene();
                    currentScene.setRoot(root);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (SQLException e) {
            CustomErrorAlert.showAlert("Ein Fehler ist aufgetreten! \n" + e.getMessage());
        }
    }

    @FXML
    protected void onAddPersonalClicked() throws IOException {
        App.setRoot("personal-add-view");

    }

    @FXML
    protected void onBackClicked() throws IOException {
        App.setRoot("main-view");

    }
}