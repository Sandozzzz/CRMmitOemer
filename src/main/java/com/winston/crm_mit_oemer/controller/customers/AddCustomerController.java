package com.winston.crm_mit_oemer.controller.customers;

import com.winston.crm_mit_oemer.App;
import com.winston.crm_mit_oemer.model.Customer;
import com.winston.crm_mit_oemer.service.CustomerManager;
import com.winston.crm_mit_oemer.service.ImageHelper;
import com.winston.crm_mit_oemer.util.CustomErrorAlert;
import com.winston.crm_mit_oemer.util.CustomerType;
import com.winston.crm_mit_oemer.util.UserType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField company;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TextField profilePhotoFromLocal;
    @FXML
    private Button takeProfilePhotoButton;
    @FXML
    private TextField profilePhotoFromUrl;
    @FXML
    private MenuButton statusMenuButton;

    @FXML
    private ToggleGroup radioGroup;

    byte[] profilePhoto;
    CustomerType customerType;

    CustomerManager customerManager = new CustomerManager();

    @FXML
    protected void onSaveCustomerClicked() throws IOException {
        if (name.getText().isEmpty() || surname.getText().isEmpty() || company.getText().isEmpty() || email.getText().isEmpty() || customerType == null) {
            CustomErrorAlert.showAlert("Bitte füllen Sie die erforderlichen Angaben aus!");
            return;
        }
        if (profilePhotoFromUrl.getText().isEmpty() && profilePhotoFromLocal.getText().isEmpty()) {
            profilePhoto = null;
        }

        if (!email.getText().matches("^[A-Za-z0-9._]+@[A-Za-z0-9]+\\.[A-Za-z0-9]{2,6}$")) {
            CustomErrorAlert.showAlert("Die Email ist ungültig!");
        }

        if (!phone.getText().isEmpty() && !phone.getText().matches("\\+?[0-9]{1,3}?[-.\\s]?\\(?[0-9]{1,4}?\\)?[-.\\s]?[0-9]{1,4}[-.\\s]?[0-9]{1,9}")) {
            CustomErrorAlert.showAlert("Telefonnummer ist ungültig!");
            return;
        }
        if (!profilePhotoFromUrl.getText().isEmpty()) {
            profilePhoto = ImageHelper.getProfilePhotoFromUrl(profilePhotoFromUrl.getText());
            System.out.println(profilePhoto);
        }
        Customer customer = new Customer(name.getText(), surname.getText(), email.getText(), UserType.CUSTOMER, phone.getText(), profilePhoto, LocalDate.now(), company.getText(), customerType);
        try {
            customerManager.save(customer);
        } catch (SQLException e) {
            CustomErrorAlert.showAlert("Fehler bei Speicherung des Customers! \n"+e.getMessage());
            e.printStackTrace();
            return;
        }
        System.out.println("Customer saved"+customer);
        // App.setRoot("add-task-view");

    }

    @FXML
    protected void onCancelClicked() throws IOException {
        App.setRoot("customer-management-view");

    }

    @FXML
    protected String getSelectedOption() {
        RadioButton selected = (RadioButton) radioGroup.getSelectedToggle();
        System.out.println("Selected option: " + selected.getText());
        switch (selected.getText()) {
            case "mit URL":
                profilePhotoFromUrl.setVisible(true);
                profilePhotoFromLocal.setVisible(false);
                takeProfilePhotoButton.setVisible(false);
                profilePhotoFromLocal.clear();
                break;
            case "vom Computer":
                profilePhotoFromUrl.setVisible(false);
                profilePhotoFromUrl.clear();
                takeProfilePhotoButton.setVisible(true);
                profilePhotoFromLocal.setVisible(true);
                break;
        }

        return selected.getText();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        profilePhotoFromUrl.setVisible(false);
    }

    @FXML
    protected void onTakeProfilePhotoClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Foto Auswählen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Photo Files", "*.png", "*.jpg", "*.jpeg")
        );
        Stage stage = (Stage) takeProfilePhotoButton.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            profilePhotoFromLocal.setText(selectedFile.getName());
            profilePhoto = ImageHelper.loadImageBytesFromFile(selectedFile.getAbsolutePath());
            System.out.println(Arrays.toString(profilePhoto));
        }
    }

    @FXML
    protected void onStatusMenuItemClicked(ActionEvent event) {
        MenuItem clickedItem = (MenuItem) event.getSource();
        String itemText = clickedItem.getText();
        statusMenuButton.setText(itemText);
        customerType = CustomerType.valueOf(itemText);
    }

}
