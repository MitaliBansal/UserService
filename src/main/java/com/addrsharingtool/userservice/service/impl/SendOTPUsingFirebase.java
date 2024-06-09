package com.addrsharingtool.userservice.service.impl;

import java.util.concurrent.TimeUnit;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.PhoneAuthProvider;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SendOTPUsingFirebase {

    public static Boolean sendOTP(String phoneNumber) {
        Boolean isVerified;

        PhoneAuthProvider.verifyPhoneNumber(
            PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(null)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential credential) {
                        log.debug("Auto-retrieval or instant verification succeeded for the phoneNumber: {}", phoneNumber);
                        isVerified = signInWithPhoneAuthCredential(credential);
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException ex) {
                        log.debug("Verification failed for the phoneNumber: {}, ex: {}", phoneNumber, ex.getMessage());
                        isVerified = false;
                    }

                    @Override
                    public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
                        log.debug("OTP sent successfully, for the phoneNumber: {}", phoneNumber);
                    }
                })
                .build());

        return isVerified;
    }

    private boolean signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    FirebaseUser user = task.getResult().getUser();
                    return true;
                } else {
                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                        log.debug("Verification failed");
                        return false;
                    }
                }
            });
    }
    
}