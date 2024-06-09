package com.addrsharingtool.userservice.service;

import com.addrsharingtool.userservice.model.request.CreateUserRequest;
import com.addrsharingtool.userservice.model.request.UpdateUserRequest;
import com.addrsharingtool.userservice.model.response.FetchUserResponse;

public interface IUserCRUDService {

    void createUser(CreateUserRequest createUserRequest);

    void updateUser(UpdateUserRequest updateUserRequest);

    FetchUserResponse fetchUser(String mobileNumber);

}