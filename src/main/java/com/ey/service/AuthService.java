package com.ey.service;


import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ey.dto.request.UserLoginRequest;
import com.ey.dto.request.UserSignupRequest;
import com.ey.dto.response.AuthResponse;
import com.ey.dto.response.UserResponse;
import com.ey.entity.User;
import com.ey.enums.UserStatus;
import com.ey.exception.BadRequestException;
import com.ey.exception.ResourceNotFoundException;
import com.ey.repository.UserRepository;
import com.ey.security.CustomUserDetails;
import com.ey.security.JwtUtil;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	public void register(UserSignupRequest request) {

		if (userRepository.existsByEmail(request.getEmail())) {
			throw new BadRequestException("Email already registered");
		}

		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setPhone(request.getPhone());
		user.setRole(request.getRole()); 
		user.setCreatedAt(LocalDateTime.now());
		user.setStatus(UserStatus.ACTIVE);
		userRepository.save(user);
		
		UserResponse response = new UserResponse();
		response.setUserId(user.getUserId());
	}

	public AuthResponse login(UserLoginRequest request) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

		String token = jwtUtil.generateToken(userDetails);

		return new AuthResponse(token);
	}

	public void forgotPassword(String email, String newPassword) {

		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));

		user.setPassword(passwordEncoder.encode(newPassword));

		userRepository.save(user);
	}

	public void logout() {

	}
}
