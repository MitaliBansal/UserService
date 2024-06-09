package com.addrsharingtool.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.addrsharingtool.userservice.model.request.CreateUserRequest;
import com.addrsharingtool.userservice.model.request.FetchUserRequest;
import com.addrsharingtool.userservice.model.request.UpdateUserRequest;
import com.addrsharingtool.userservice.model.response.FetchUserResponse;
import com.addrsharingtool.userservice.service.IUserCRUDService;
import com.addrsharingtool.userservice.service.impl.UserCRUDServiceImpl;

import static com.addrsharingtool.userservice.constant.APIPath.User.CREATE_USER;
import static com.addrsharingtool.userservice.constant.APIPath.User.UPDATE_USER;
import static com.addrsharingtool.userservice.constant.APIPath.User.FETCH_USER;
import static com.addrsharingtool.userservice.constant.APIResponseString.CREATE_USER_API_SUCCESS_RESPONSE;
import static com.addrsharingtool.userservice.constant.APIResponseString.UPDATE_USER_API_SUCCESS_RESPONSE;

@RestController
public class UserCRUDController {
    
    private IUserCRUDService userCRUDService;

    public UserCRUDController(UserCRUDServiceImpl userCRUDServiceImpl) {
        this.userCRUDService = userCRUDServiceImpl;
    }

    @PostMapping(value = CREATE_USER)
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest createUserRequest) {
        userCRUDService.createUser(createUserRequest);
        return ResponseEntity.ok().body(CREATE_USER_API_SUCCESS_RESPONSE);
    }

    @PostMapping(value = UPDATE_USER)
    public ResponseEntity<String> updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        userCRUDService.updateUser(updateUserRequest);
        return ResponseEntity.ok().body(UPDATE_USER_API_SUCCESS_RESPONSE);
    }

    @GetMapping(value = FETCH_USER)
    public ResponseEntity<FetchUserResponse> fetchUser(@RequestBody FetchUserRequest fetchUserRequest) {
        return ResponseEntity.ok().body(userCRUDService.fetchUser(fetchUserRequest.getMobileNumber()));
    }

}