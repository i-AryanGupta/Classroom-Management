package com.cam.service;

import org.springframework.http.ResponseEntity;

import com.cam.enums.UserRole;
import com.cam.requestdto.UserRequest;
import com.cam.responsedto.UserResponse;
import com.cam.utility.ResponseStructure;

public interface UserService {

	ResponseEntity<ResponseStructure<UserResponse>> addUser(UserRequest userRequest, UserRole userRole);
	

}
