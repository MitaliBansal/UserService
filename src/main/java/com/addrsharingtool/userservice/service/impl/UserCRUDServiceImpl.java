package com.addrsharingtool.userservice.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.addrsharingtool.userservice.dao.mysql.entity.User;
import com.addrsharingtool.userservice.dao.mysql.repository.UserRepository;
import com.addrsharingtool.userservice.exception.AlreadyExistsException;
import com.addrsharingtool.userservice.model.request.CreateUserRequest;
import com.addrsharingtool.userservice.model.request.UpdateUserRequest;
import com.addrsharingtool.userservice.model.response.FetchUserResponse;
import com.addrsharingtool.userservice.service.IUserCRUDService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserCRUDServiceImpl implements IUserCRUDService {

    private UserRepository userRepository;

    public UserCRUDServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(CreateUserRequest createUserRequest) {
        log.debug("Received request for createUser: {}", createUserRequest);
        validateCreateUserRequest(createUserRequest);
        sendVerificationEmail(createUserRequest.getEmailAddress());
        User user = User.builder().firstName(createUserRequest.getFirstName())
            .lastName(createUserRequest.getLastName())
            .dob(getDobInLocalDateFormat(createUserRequest.getDob()))
            .emailAddress(createUserRequest.getEmailAddress())
            .mobileNumber(createUserRequest.getMobileNumber()).build();

        userRepository.save(user);
    }

    public void updateUser(UpdateUserRequest updateUserRequest) {
        log.debug("Received request for updateUser: {}", updateUserRequest);
        Optional<User> user = userRepository.findByMobileNumber(updateUserRequest.getPreviousMobileNumber());
        if (!user.isPresent()) {
            throw new EntityNotFoundException("User not present, invalid operation");
        }
        
        User updatedUser = validateUpdateUserRequestAndUpdateData(user.get(), updateUserRequest);
        userRepository.save(updatedUser);
    }

    public FetchUserResponse fetchUser(String mobileNumber) {
        log.debug("Received request for fetching user: {}", mobileNumber);
        Optional<User> user = userRepository.findByMobileNumber(mobileNumber);
        if (!user.isPresent()) {
            throw new EntityNotFoundException("User not registered, invalid operation");
        }

        return FetchUserResponse.builder().firstName(user.get().getFirstName())
                                .lastName(user.get().getLastName())
                                .dob(user.get().getDob())
                                .emailAddress(user.get().getEmailAddress())
                                .mobileNumber(user.get().getMobileNumber())
                                .build();
    }

    private void validateCreateUserRequest(CreateUserRequest createUserRequest) {
        Optional<User> user = userRepository.findByMobileNumber(createUserRequest.getMobileNumber());
        if (user.isPresent()) {
            throw new AlreadyExistsException("this mobile_number is registered with another user, please enter new mobile_number");
        }

        user = userRepository.findByEmailAddress(createUserRequest.getEmailAddress());
        if (user.isPresent()) {
            throw new AlreadyExistsException("this email_address is registered with another user, please enter new email_address");
        }
    }

    private User validateUpdateUserRequestAndUpdateData(User user, UpdateUserRequest updateUserRequest) {
        if (!StringUtils.isEmpty(updateUserRequest.getUpdatedMobileNumber())) {
            if (user.getMobileNumber() != updateUserRequest.getUpdatedMobileNumber()) {
                Optional<User> optionalUser = userRepository.findByMobileNumber(updateUserRequest.getUpdatedMobileNumber());
                if (optionalUser.isPresent()) {
                    throw new AlreadyExistsException("this mobile_number is registered with another user, please enter new mobile_number to update");
                }
            }

            user.setMobileNumber(updateUserRequest.getUpdatedMobileNumber());
        }

        if (!StringUtils.isEmpty(updateUserRequest.getEmailAddress())) {
            if (user.getEmailAddress() != updateUserRequest.getEmailAddress()) {
                Optional<User> optionalUser = userRepository.findByEmailAddress(updateUserRequest.getEmailAddress());
                if (optionalUser.isPresent()) {
                    throw new AlreadyExistsException("this email_address is registered with another user, please enter new email_address to update");
                }
            }

            sendVerificationEmail(updateUserRequest.getEmailAddress());
            user.setEmailAddress(updateUserRequest.getEmailAddress());
        }

        if (!StringUtils.isEmpty(updateUserRequest.getFirstName())) {
            user.setFirstName(updateUserRequest.getFirstName());
        }

        if (!StringUtils.isEmpty(updateUserRequest.getLastName())) {
            user.setLastName(updateUserRequest.getLastName());
        }

        if (!StringUtils.isEmpty(updateUserRequest.getDob())) {
            user.setDob(getDobInLocalDateFormat(updateUserRequest.getDob()));
        }

        return user;
    }

    private LocalDate getDobInLocalDateFormat(String dob) {
        String[] splitDob = dob.split("/");
        return LocalDate.of(Integer.valueOf(splitDob[2]), Integer.valueOf(splitDob[1]), Integer.valueOf(splitDob[0]));
    }
    
}