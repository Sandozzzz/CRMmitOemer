package com.winston.crm_mit_oemer.controller;

import com.winston.crm_mit_oemer.App;
import com.winston.crm_mit_oemer.model.Customer;
import com.winston.crm_mit_oemer.model.Person;
import com.winston.crm_mit_oemer.model.User;
import com.winston.crm_mit_oemer.service.ImageHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Locale;

public class DetailViewController {
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
        }
        else{
            detail.setText(person.getStatus().name().toLowerCase());
        }
    }

    @FXML
    protected void onSaveButtonClicked() throws IOException {
        App.setRoot("personal-add-view");

    }

    @FXML
    protected void onCancelClicked() throws IOException {
        App.setRoot("main-view");

    }
}
