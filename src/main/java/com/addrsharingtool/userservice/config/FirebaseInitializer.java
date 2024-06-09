package com.addrsharingtool.userservice.config;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class FirebaseInitializer {

    public static void initialize() {
        try {
            FileInputStream fileInputStream = new FileInputStream("firebaseServiceAccountKey.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(fileInputStream))
                    .build();
    
            FirebaseApp.initializeApp(options);
        } catch(IOException ex) {
            log.error("Exception in initializing the firebase SDK: {}", ex.getMessage());
        }
    }
    
}