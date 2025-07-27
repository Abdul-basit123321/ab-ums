package com.abdul.ums;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(
		title = "User Management System",
		version = "1.0",
		description = "API for managing users and roles"
))
@SpringBootApplication
public class UserManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementSystemApplication.class, args);
		System.out.println("Management System started");
	}

}
