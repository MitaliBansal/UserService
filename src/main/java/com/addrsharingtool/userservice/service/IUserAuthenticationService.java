package com.addrsharingtool.userservice.service;

import com.addrsharingtool.userservice.model.request.SendOTPRequest;
import com.addrsharingtool.userservice.model.request.VerifyOTPRequest;

public interface IUserAuthenticationService {

    void sendOTPtoUser(SendOTPRequest sendOTPRequest);
    
    void verifyOTP(VerifyOTPRequest verifyOTPRequest);

    void verifyUserToken(String token);
    
}