package com.winston.crm_mit_oemer.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;


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

}
