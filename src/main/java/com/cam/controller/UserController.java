package com.cam.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cam.enums.UserRole;
import com.cam.requestdto.UserRequest;
import com.cam.responsedto.UserResponse;
import com.cam.service.UserService;
import com.cam.utility.ResponseStructure;

@RestController
@RequestMapping("/api/version1")
public class UserController {
	
	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	
	@PostMapping("/registers/students")
	public ResponseEntity<ResponseStructure<UserResponse>> addStudent(@RequestBody UserRequest userRequest)
	{
		return userService.addUser(userRequest, UserRole.STUDENT);	
	}
	
	@PostMapping("/registers/teachers")
	public ResponseEntity<ResponseStructure<UserResponse>> addTeacher(@RequestBody UserRequest userRequest)
	{
		return userService.addUser(userRequest, UserRole.STUDENT);	
	}

}
