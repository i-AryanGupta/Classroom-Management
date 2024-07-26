package com.cam.responsedto;

import com.cam.enums.UserRole;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
	private int userId;
	private String username;
	private String email;
	private boolean isEmailVerified;
	private boolean isDeleted;
	@Enumerated(EnumType.STRING)
	private UserRole userRole;
}
