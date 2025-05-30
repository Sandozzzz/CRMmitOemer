package com.winston.crm_mit_oemer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        stage.getIcons().add(
                new Image(Objects.requireNonNull(
                        getClass().getResourceAsStream("/com/winston/crm_mit_oemer/assets/logo1.png"))));
        // Baut die Objekte nach der Vorgabe der FXML-Datei
        scene = new Scene(loadFXML("login-view"));
        stage.setTitle("CRM");
        stage.setScene(scene);
        stage.show();
    }

    // Überschreibt die sichtbare View
   public static void setRoot(String fxml) throws IOException {
        scene.setRoot   (loadFXML(fxml));
    }

    // Lädt die FXML-Datei und baut daraus die GUI
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/"+fxml + ".fxml"));
        return  fxmlLoader.load();
    }


    public static void main(String[] args) {
        launch();
    }
}