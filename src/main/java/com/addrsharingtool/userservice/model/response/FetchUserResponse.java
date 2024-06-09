package com.addrsharingtool.userservice.model.response;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FetchUserResponse {

    @JsonProperty(value = "first_name")
    private String firstName;

    @JsonProperty(value = "last_name")
    private String lastName;

    @JsonProperty(value = "dob")
    private LocalDate dob;

    @JsonProperty(value = "email_address")
    private String emailAddress;
    
    @JsonProperty(value = "mobile_number")
    private String mobileNumber;
    
}