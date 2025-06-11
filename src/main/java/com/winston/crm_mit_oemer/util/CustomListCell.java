package com.winston.crm_mit_oemer.util;


import com.winston.crm_mit_oemer.model.Customer;
import com.winston.crm_mit_oemer.model.Person;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;

import java.io.ByteArrayInputStream;
import java.util.Objects;

public class CustomListCell extends ListCell<Customer> {
    private final HBox content;
    private final ImageView imageView;
    private final VBox vbox;
    private final Label nameLabel;
    private final Label companyLabel;

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

        companyLabel = new Label();
        companyLabel.setId("cell-label");

        vbox = new VBox(nameLabel, companyLabel);
        vbox.setSpacing(2);
        vbox.setAlignment(Pos.CENTER_LEFT);
        content = new HBox(imageView, vbox);
        content.setSpacing(10);
        content.setAlignment(Pos.CENTER_LEFT);
    }

    @Override
    protected void updateItem(Customer customer, boolean empty) {
        super.updateItem(customer, empty);
        if (empty || customer == null) {
            setGraphic(null);
            setBackground(null);
        } else {
            if(customer.getProfilePhoto() != null){
                Image image = new Image(new ByteArrayInputStream(customer.getProfilePhoto()));
                imageView.setImage(image);
            }else {
                imageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                        "/com/winston/crm_mit_oemer/assets/user.png"))));

            }
            nameLabel.setText(customer.getName()+" "+customer.getSurname());
            companyLabel.setText(customer.getCompany());
            setGraphic(content);
            setId("list-cell");
        }
    }


}
