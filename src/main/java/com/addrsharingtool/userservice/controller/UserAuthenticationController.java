package com.addrsharingtool.userservice.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.addrsharingtool.userservice.exception.BadRequestException;
import com.addrsharingtool.userservice.model.request.SendOTPRequest;
import com.addrsharingtool.userservice.model.request.VerifyOTPRequest;
import com.addrsharingtool.userservice.service.IUserAuthenticationService;
import com.addrsharingtool.userservice.service.impl.UserAuthenticationServiceImpl;

import static com.addrsharingtool.userservice.constant.APIPath.UserVerification.VERIFY_OTP;
import static com.addrsharingtool.userservice.constant.APIPath.UserVerification.SEND_OTP;
import static com.addrsharingtool.userservice.constant.APIPath.UserVerification.VERIFY_TOKEN;

@RestController
public class UserAuthenticationController {

    public IUserAuthenticationService userAuthenticationService;

    public UserAuthenticationController(UserAuthenticationServiceImpl userAuthenticationServiceImpl) {
        this.userAuthenticationService = userAuthenticationServiceImpl;
    }

    @PostMapping(value = SEND_OTP)
    public void sendOTPtoUser(@RequestBody SendOTPRequest sendOTPRequest) {
        userAuthenticationService.sendOTPtoUser(sendOTPRequest);
    }

    @PostMapping(value = VERIFY_OTP)
    public void verifyOTP(@RequestBody VerifyOTPRequest verifyOTPRequest) {
        userAuthenticationService.verifyOTP(verifyOTPRequest);
    }

    @PostMapping(value = VERIFY_TOKEN)
    public void verifyUserToken(@RequestHeader(value = "Authentication") String token) {
        if (StringUtils.isEmpty(token)) {
            throw new BadRequestException("token can't be null or empty");
        }

        userAuthenticationService.verifyUserToken(token);
    }
    
}