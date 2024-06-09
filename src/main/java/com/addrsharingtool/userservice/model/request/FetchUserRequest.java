package com.addrsharingtool.userservice.model.request;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FetchUserRequest {

    @Size(min = 10, max = 10, message = "Mobile number must contains 10 digits")
    @JsonProperty(value = "mobile_number")
    private String mobileNumber;
    
}