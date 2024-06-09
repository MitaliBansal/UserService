package com.addrsharingtool.userservice.service.impl;

import java.util.Objects;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.addrsharingtool.userservice.dao.mysql.entity.User;
import com.addrsharingtool.userservice.dao.mysql.repository.UserRepository;
import com.addrsharingtool.userservice.exception.BadRequestException;
import com.addrsharingtool.userservice.exception.UnAuthorizedRequestException;
import com.addrsharingtool.userservice.model.request.SendOTPRequest;
import com.addrsharingtool.userservice.model.request.VerifyOTPRequest;
import com.addrsharingtool.userservice.service.IUserAuthenticationService;
import lombok.extern.log4j.Log4j2;

import static com.addrsharingtool.userservice.service.impl.SendOTPUsingFirebase.sendOTP;
import static com.addrsharingtool.userservice.service.impl.VerifyOTPUsingFirebase.verifyOTPUsingFirebase;

@Log4j2
@Service
public class UserAuthenticationServiceImpl implements IUserAuthenticationService {

    private UserRepository userRepository;

    public UserAuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
 
    public void sendOTPtoUser(SendOTPRequest sendOTPRequest) {
        log.debug("Received request to send otp to the user: {}", sendOTPRequest.getMobileNumber());
        Boolean isVerified = sendOTP(sendOTPRequest.getMobileNumber());
        if (Objects.isNull(isVerified)) {
            return null;
        }
        
        if (isVerified) {
            onSuccessfulVerification(sendOTPRequest.getMobileNumber());
        } else {
            onVerificationFailure();
        }
    }
    
    public void verifyOTP(VerifyOTPRequest verifyOTPRequest) {
        log.debug("Received request to verify otp for the user: {}, otp: {}", verifyOTPRequest.getMobileNumber(), verifyOTPRequest.getOtp());
        boolean isVerified = verifyOTPUsingFirebase(verifyOTPRequest.getMobileNumber(), verifyOTPRequest.getOtp());
        
        if (isVerified) {
            onSuccessfulVerification(verifyOTPRequest.getMobileNumber());
        } else {
            onVerificationFailure();
        }
    }

    public void verifyUserToken(String token) {
        if (isValidToken(token)) {
            return user;
        } else {
            throw new UnAuthorizedRequestException("The token is invalid");
        }
    }

    private void onSuccessfulVerification(String phoneNumber) {
        Optional<User> user = userRepository.findByMobileNumber(phoneNumber);
        if (user.isPresent()) {
            //send user info along with JWT token        
        } else {

        }
    }

    private void onVerificationFailure() {
        
    }
    
}