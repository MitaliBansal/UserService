package com.addrsharingtool.userservice.service.impl;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.PhoneAuthProvider;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class VerifyOTPUsingFirebase {

    public static boolean verifyOTPUsingFirebase(String verificationId, String otp) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, otp);
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    FirebaseUser user = task.getResult().getUser();
                    log.debug("Verification successful for the phoneNumber: {}", verificationId);
                    return true;
                } else {
                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                        log.debug("Verification failed for the phoneNumber: {}", verificationId);
                        return false;
                    }
                }
            });
    }

}