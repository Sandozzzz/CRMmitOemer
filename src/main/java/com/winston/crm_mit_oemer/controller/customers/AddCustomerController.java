package com.winston.crm_mit_oemer.controller.customers;

import com.winston.crm_mit_oemer.App;
import com.winston.crm_mit_oemer.model.Customer;
import com.winston.crm_mit_oemer.service.ImageHelper;
import com.winston.crm_mit_oemer.util.StatusType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
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
    private RadioButton urlOption;

    @FXML
    private RadioButton fromLocalOption;

    @FXML
    private ToggleGroup radioGroup;

    byte[] profilePhoto;

    @FXML
    protected void onSaveCustomerClicked() throws IOException {
        if (name.getText().isEmpty() || surname.getText().isEmpty() || company.getText().isEmpty() || email.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Bitte füllen Sie die erforderlichen Angaben aus!");
            alert.showAndWait();
            return;
        }
        if (profilePhotoFromUrl.getText().isEmpty() && profilePhotoFromLocal.getText().isEmpty()) {
profilePhoto = null;
        }
            if (!profilePhotoFromUrl.getText().isEmpty()) {
              profilePhoto =  ImageHelper.getProfilePhotoFromUrl(profilePhotoFromUrl.getText());
                System.out.println(profilePhoto);
        }
        Customer customer = new Customer(name.getText(), name.getText(), email.getText(), StatusType.CUSTOMER, phone.getText(),profilePhoto, LocalDate.now(), company.getText());
        System.out.println(customer);
String ss = getSelectedOption();
        System.out.println(ss);
        System.out.println("Customer saved");
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

        return selected != null ? selected.getText() : "Nothing is selected";
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


}
