package com.winston.crm_mit_oemer.controller.personals;

import com.winston.crm_mit_oemer.App;
import com.winston.crm_mit_oemer.model.User;
import com.winston.crm_mit_oemer.service.ImageHelper;
import com.winston.crm_mit_oemer.service.UserManager;
import com.winston.crm_mit_oemer.util.CustomErrorAlert;
import com.winston.crm_mit_oemer.util.UserType;
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
import java.util.ResourceBundle;

public class AddPersonalController implements Initializable {
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField phone;
    @FXML
    private TextField profilePhotoFromLocal;
    @FXML
    private Button takeProfilePhotoButton;
    @FXML
    private TextField profilePhotoFromUrl;
    @FXML
    private ToggleGroup radioGroup;

    User user;
    byte[] profilePhoto;
    boolean isEditMode = false;
    boolean saveResult = false;
    UserManager userManager = new UserManager();


    public void setPerson(User user) {
        this.user = user;
        updateView();
        isEditMode = true;
    }

    private void updateView() {
        name.setText(user.getName());
        surname.setText(user.getSurname());
        email.setText(user.getEmail());
        phone.setText(user.getPhone());
        profilePhoto = user.getProfilePhoto();
        if (profilePhoto != null) profilePhotoFromLocal.setText("Profile_Photo.png");
        password.setDisable(true);


    }


    @FXML
    protected void onSavePersonalClicked() throws IOException {
        if (name.getText().isEmpty() || surname.getText().isEmpty() || email.getText().isEmpty()) {
            CustomErrorAlert.showAlert("Bitte füllen Sie die erforderlichen Angaben aus!");
            return;
        }
        if (!isEditMode && password.getText().isEmpty() && password.getText().length() < 6) {
            CustomErrorAlert.showAlert("Das Passwort ist zu kurz!");
            return;
        }
        if (!email.getText().matches("^[A-Za-z0-9._]+@[A-Za-z0-9]+\\.[A-Za-z0-9]{2,6}$")) {
            CustomErrorAlert.showAlert("Die Email ist ungültig!");
            return;
        }
        if (!phone.getText().isEmpty() && !phone.getText().matches("\\+?[0-9]{1,3}?[-.\\s]?\\(?[0-9]{1,4}?\\)?[-.\\s]?[0-9]{1,4}[-.\\s]?[0-9]{1,9}")) {
            CustomErrorAlert.showAlert("Telefonnummer ist ungültig!");
            return;
        }

        if (!isEditMode && profilePhotoFromUrl.getText().isEmpty() && profilePhotoFromLocal.getText().isEmpty()) {
            profilePhoto = null;
        }
        if (!profilePhotoFromUrl.getText().isEmpty()) {
            profilePhoto = ImageHelper.getProfilePhotoFromUrl(profilePhotoFromUrl.getText());
        }
        try {

            if (!isEditMode) {
                //create a new Personal
                user = new User(name.getText(), surname.getText(), email.getText(), password.getText(), UserType.PERSONAL, phone.getText(), profilePhoto, LocalDate.now());

                saveResult = userManager.save(user);
            } else {
                //update the Personal
                user.setName(name.getText());
                user.setSurname(surname.getText());
                user.setEmail(email.getText());
                user.setPhone(phone.getText());
                user.setProfilePhoto(profilePhoto);

                saveResult = userManager.update(user);
            }
            if (saveResult) {
                clearFields();
                App.setRoot("personal-management-view");
            }

        } catch (SQLException e) {
            CustomErrorAlert.showAlert("Fehler bei Speicherung des Users! \n" + e.getMessage());
            e.printStackTrace();
            return;
        }

        System.out.println("Personal saved" + user);
    }

    @FXML
    protected void onCancelClicked() throws IOException {
        App.setRoot("personal-management-view");

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
        }
    }

    private void clearFields() {
        name.clear();
        surname.clear();
        password.clear();
        email.clear();
        phone.clear();
        profilePhotoFromUrl.clear();
        profilePhotoFromLocal.clear();
        profilePhoto = null;
    }


}

