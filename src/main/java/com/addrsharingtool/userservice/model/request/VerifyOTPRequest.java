package com.addrsharingtool.userservice.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VerifyOTPRequest {

    @Size(min = 10, max = 10, message = "Mobile number must contains 10 digits")
    @JsonProperty(value = "mobile_number")
    private String mobileNumber;

    @NotBlank(message = "otp can't be blank or empty")
    private String otp;
    
}