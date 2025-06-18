package com.winston.crm_mit_oemer.util;


import com.winston.crm_mit_oemer.model.Customer;
import com.winston.crm_mit_oemer.model.Person;
import com.winston.crm_mit_oemer.model.User;
import com.winston.crm_mit_oemer.service.ImageHelper;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;

import java.io.ByteArrayInputStream;
import java.util.Objects;

public class CustomListCell extends ListCell<Person> {
    private final HBox content;
    private final ImageView imageView;
    private final Label nameLabel;
    private final Label subLabel;
    private final Label subLabel2;

    public CustomListCell() {
        imageView = new ImageView();
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        imageView.setPreserveRatio(false);
        Circle clip = new Circle(25, 25, 25);
        imageView.setClip(clip);


        nameLabel = new Label();
        nameLabel.setId("cell-label");
        nameLabel.setStyle("-fx-font-weight: bold");

        subLabel = new Label();
        subLabel.setId("cell-label");

        subLabel2 = new Label();
        subLabel2.setId("cell-label");

        VBox vbox = new VBox(nameLabel, subLabel,subLabel2);
        vbox.setSpacing(2);
        vbox.setAlignment(Pos.CENTER_LEFT);
        content = new HBox(imageView, vbox);
        content.setSpacing(10);
        content.setAlignment(Pos.CENTER_LEFT);
    }

    @Override
    protected void updateItem(Person person, boolean empty) {
        super.updateItem(person, empty);
        if (empty || person == null) {
            setGraphic(null);
            setBackground(null);
        } else {
            imageView.setImage(ImageHelper.changeToImage(person.getProfilePhoto()));
            nameLabel.setText(person.getName() + " " + person.getSurname());
            if (person instanceof Customer) {
                subLabel.setText(((Customer) person).getCompany());
                subLabel2.setText(((Customer) person).getCustomerType().name().toLowerCase());
            }
            if (person instanceof User) {
                subLabel.setText(person.getEmail());
            }

            setGraphic(content);
            setId("list-cell");
        }
    }


}
