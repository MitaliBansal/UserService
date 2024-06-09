package com.addrsharingtool.userservice.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class SendOTPRequest {

    @Size(min = 10, max = 10, message = "Mobile number must contains 10 digits")
    @JsonProperty(value = "mobile_number")
    private String mobileNumber;
    
}