package com.winston.crm_mit_oemer.controller.customers;

import com.winston.crm_mit_oemer.App;
import com.winston.crm_mit_oemer.model.Customer;
import com.winston.crm_mit_oemer.model.Person;
import com.winston.crm_mit_oemer.service.CustomerManager;
import com.winston.crm_mit_oemer.util.CustomErrorAlert;
import com.winston.crm_mit_oemer.util.CustomListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerManagementController implements Initializable {

    @FXML
    private ListView<Person> customerListView;

    CustomerManager customerManager = new CustomerManager();
    ObservableList<Person> observableList;


    @FXML
    protected void onAddCustomerClicked() throws IOException {
        App.setRoot("customer-add-view");

    }

    @FXML
    protected void onBackClicked() throws IOException {
        App.setRoot("main-view");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            List<Customer> customers = customerManager.findAll();
            System.out.println(customers);
            observableList = FXCollections.observableArrayList(customers);
            customerListView.setItems(observableList);

            customerListView.setCellFactory(param -> new CustomListCell());
        } catch (SQLException e) {
            CustomErrorAlert.showAlert("Ein Fehler ist aufgetreten! \n" + e.getMessage());
        }
    }
}
