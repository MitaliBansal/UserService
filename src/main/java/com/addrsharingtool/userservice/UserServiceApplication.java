package com.addrsharingtool.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(UserServiceApplication.class, args);
		} catch(Exception ex) {
			log.error("Error in running UserServiceApplication : {}", ex.getMessage());
		}
	}

}