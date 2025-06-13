package com.winston.crm_mit_oemer.controller;

import com.winston.crm_mit_oemer.App;
import com.winston.crm_mit_oemer.controller.customers.AddCustomerController;
import com.winston.crm_mit_oemer.controller.personals.AddPersonalController;
import com.winston.crm_mit_oemer.model.Customer;
import com.winston.crm_mit_oemer.model.Person;
import com.winston.crm_mit_oemer.model.User;
import com.winston.crm_mit_oemer.service.ImageHelper;
import com.winston.crm_mit_oemer.util.CustomErrorAlert;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import org.w3c.dom.events.Event;

import java.io.IOException;
import java.util.Locale;

public class DetailViewController {

    FXMLLoader loader;
    Parent root;
    @FXML
    private Label name;
    @FXML
    private Label email;
    @FXML
    private Label phone;
    @FXML
    private ImageView profilePhoto;
    @FXML
    private Label detail;
    @FXML
    private TextArea note;
    @FXML
    private Button editButton;

    Person person;

    public void setPerson(Person person) {
        this.person = person;
        updateView();
    }

    private void updateView() {
        name.setText(person.getName() + " " + person.getSurname());
        email.setText(person.getEmail());
        phone.setText(person.getPhone());
        profilePhoto.setImage(ImageHelper.changeToImage(person.getProfilePhoto()));
        profilePhoto.setPreserveRatio(false);
        Circle clip = new Circle(75, 75, 75);
        profilePhoto.setClip(clip);
        if (person instanceof Customer) {

            detail.setText(((Customer) person).getCompany());
        } else {
            detail.setText(person.getStatus().name().toLowerCase());
        }
    }

    @FXML
    protected void onSaveButtonClicked() throws IOException {
        if (person instanceof Customer) {

            App.setRoot("customer-management-view");
        } else {
            App.setRoot("personal-management-view");
        }


    }

    @FXML
    protected void onCancelClicked() throws IOException {
        if (person instanceof Customer) {

            App.setRoot("customer-management-view");
        } else {
            App.setRoot("personal-management-view");
        }

    }

    @FXML
    protected void onEditButtonClicked()  {
        try {
            if (person instanceof Customer) {

                loader = new FXMLLoader(getClass().getResource("/com/winston/crm_mit_oemer/views/customer-add-view.fxml"));
                root = loader.load();
                AddCustomerController controller = loader.getController();
                controller.setPerson((Customer) person);
            } else {
                loader = new FXMLLoader(getClass().getResource("/com/winston/crm_mit_oemer/views/personal-add-view.fxml"));
                root = loader.load();
                AddPersonalController controller = loader.getController();
                controller.setPerson((User) person);
            }


            Scene currentScene = editButton.getScene();
            currentScene.setRoot(root);

        } catch (IOException e) {
            CustomErrorAlert.showAlert("Fehler bei Bearbeitung!" + e.getMessage());
        }
    }
}
