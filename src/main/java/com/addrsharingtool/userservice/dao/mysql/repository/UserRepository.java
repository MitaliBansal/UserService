package com.addrsharingtool.userservice.dao.mysql.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.addrsharingtool.userservice.dao.mysql.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByMobileNumber(String mobileNumber);

    Optional<User> findByEmailAddress(String emailAddress);

}