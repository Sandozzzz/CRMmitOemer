package com.winston.crm_mit_oemer.service;

import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.Objects;


public class ImageHelper {

    public static byte[] loadImageBytesFromFile(String path) {

        try {
            File file = new File(path);
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] getProfilePhotoFromUrl(String url) throws IOException {

        try(InputStream input = new URL(url).openStream()) {

            return input.readAllBytes();

        }
    }

    public static Image changeToImage (byte[] imageBytes){
        if(imageBytes != null){
            return new Image(new ByteArrayInputStream(imageBytes));

        }else {
            return new Image(Objects.requireNonNull(ImageHelper.class.getResourceAsStream(
                    "/com/winston/crm_mit_oemer/assets/user.png")));

        }
    }

}
