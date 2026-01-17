package com.ey.mapper;

import java.time.LocalDateTime;

import com.ey.dto.request.UserSignupRequest;
import com.ey.dto.request.UserUpdateRequest;
import com.ey.dto.response.UserResponse;
import com.ey.entity.User;
import com.ey.enums.UserStatus;

public class UserMapper {
	
	public static User toEntity(UserSignupRequest request) {
		
		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());
		user.setPhone(request.getPhone());
		user.setRole(request.getRole());
		user.setStatus(UserStatus.ACTIVE);
		user.setCreatedAt(LocalDateTime.now());
		return user;
	}
	public static void updateEntity(User user, UserUpdateRequest request) {
		user.setName(request.getName());
		user.setPhone(request.getPhone());
	}
	public static UserResponse toResponse(User user) {
		UserResponse response = new UserResponse();
		response.setUserId(user.getUserId());
		response.setName(user.getName());
		response.setEmail(user.getEmail());
		response.setPhone(user.getPhone());
		response.setRole(user.getRole());
		response.setStatus(user.getStatus());
		response.setCreatedAt(user.getCreatedAt());
		return response;

	}

}
