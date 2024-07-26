package com.cam.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cam.entity.Student;
import com.cam.entity.Teacher;
import com.cam.entity.User;
import com.cam.enums.UserRole;
import com.cam.exception.UserAlreadyExistException;
import com.cam.mapper.UserMapper;
import com.cam.repository.UserRepository;
import com.cam.requestdto.UserRequest;
import com.cam.responsedto.UserResponse;
import com.cam.service.UserService;
import com.cam.utility.ResponseStructure;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	private UserMapper userMapper;
	
	

	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
		super();
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}
	
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> addUser(UserRequest userRequest, UserRole userRole) {
		
		boolean existsUser = userRepository.existsByEmail(userRequest.getEmail());
		
		 if(existsUser)
			 throw new UserAlreadyExistException("Email :" + userRequest.getEmail() +" is already exist");
		 
		 User user = null;
		 switch(userRole)
		 {
		 	case STUDENT -> user = new Student();
		 	case TEACHER -> user = new Teacher();
		 }
		 
		 if(user != null)
			{
				user.setUserRole(userRole);
				user = userMapper.maptoUserRequest(userRequest, user);
				user.setEmailVerified(true);
				userRepository.save(user);
			}
		 
		 return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new ResponseStructure<UserResponse>()
							.setData(userMapper.maptoUserResponse(user))
							.setMessage("Added in cahce")
							.setStatus(HttpStatus.ACCEPTED.value()));

	}

}
