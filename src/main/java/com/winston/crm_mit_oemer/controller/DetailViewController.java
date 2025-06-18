package com.winston.crm_mit_oemer.controller;

import com.winston.crm_mit_oemer.App;
import com.winston.crm_mit_oemer.controller.customers.AddCustomerController;
import com.winston.crm_mit_oemer.controller.personals.AddPersonalController;
import com.winston.crm_mit_oemer.model.Customer;
import com.winston.crm_mit_oemer.model.Note;
import com.winston.crm_mit_oemer.model.Person;
import com.winston.crm_mit_oemer.model.User;
import com.winston.crm_mit_oemer.service.CustomerManager;
import com.winston.crm_mit_oemer.service.ImageHelper;
import com.winston.crm_mit_oemer.service.UserManager;
import com.winston.crm_mit_oemer.util.CustomErrorAlert;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

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
    private TextArea noteArea;
    @FXML
    private Button editButton;

    boolean haveNote = false;
    Note note;
    Person person;
    UserManager userManager;
    CustomerManager customerManager;

    public void setPerson(Person person) {
        this.person = person;
        updateView();
        takeNote();
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
        boolean result;
        try {
            if (person instanceof Customer) {
                customerManager = new CustomerManager();
               if (!haveNote) {
                   note = new Note(0,noteArea.getText(), person.getId(), 0, LocalDate.now());
                 result=  customerManager.addNote(note);
               }else{
                   note.setNote(noteArea.getText());
                   note.setCreatedDate( LocalDate.now());
                   result=   customerManager.updateNote(note);
               }
               if(result){
                   App.setRoot("customer-management-view");
               }

            } else {
                userManager = new UserManager();
                if (!haveNote) {
                    note = new Note(0,noteArea.getText(),0, person.getId(),  LocalDate.now());
                    result=  userManager.addNote(note);
                }else{
                    note.setNote(noteArea.getText());
                    note.setCreatedDate( LocalDate.now());
                    result=  userManager.updateNote(note);
                }
                if(result){
                    App.setRoot("personal-management-view");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
    protected void onEditButtonClicked() {
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


    public void takeNote() {
        try {
            if (person instanceof Customer) {
                customerManager = new CustomerManager();
               note = customerManager.findNoteById(person.getId());
               } else {
                userManager = new UserManager();
                note = userManager.findNoteById(person.getId());
            }

            if (note != null) {
                noteArea.setText(note.getNote());
                haveNote = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
