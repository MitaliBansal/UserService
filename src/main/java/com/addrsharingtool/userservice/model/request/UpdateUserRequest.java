package com.addrsharingtool.userservice.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UpdateUserRequest {

    @Size(min = 10, max = 10, message = "Phone number must contains 10 digits")
    @JsonProperty(value = "previous_mobile_number")
    private String previousMobileNumber;
    
    @JsonProperty(value = "first_name")
    private String firstName;

    @JsonProperty(value = "last_name")
    private String lastName;

    @JsonProperty(value = "dob")
    private String dob;

    @Size(min = 10, max = 10, message = "Phone number must contains 10 digits")
    @JsonProperty(value = "updated_mobile_number")
    private String updatedMobileNumber;

    @Email(regexp = ".+@.+\\..+", message = "Please use a valid email address")
    @JsonProperty(value = "email_address")
    private String emailAddress;

}