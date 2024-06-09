package com.addrsharingtool.userservice.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CreateUserRequest {
    
    @NotBlank(message = "firstName can not be blank")
    @JsonProperty(value = "first_name")
    private String firstName;

    @NotBlank(message = "lastName can not be blank")
    @JsonProperty(value = "last_name")
    private String lastName;

    @NotBlank(message = "dob can not be blank")
    @JsonProperty(value = "dob")
    private String dob;

    @Size(min = 10, max = 10, message = "Mobile number must contains 10 digits")
    @JsonProperty(value = "mobile_number")
    private String mobileNumber;
    
    @Email(regexp = ".+@.+\\..+", message = "Please use a valid email address")
    @JsonProperty(value = "email_address")
    private String emailAddress;

}