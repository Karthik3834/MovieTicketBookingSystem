package com.ey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.UserLoginRequest;
import com.ey.dto.request.UserSignupRequest;
import com.ey.dto.request.UserUpdateRequest;
import com.ey.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@Valid @RequestBody UserSignupRequest request){
		return userService.signup(request);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody UserLoginRequest request){
		return userService.login(request);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable Long userId){
		return userService.getUserById(userId);
	}
	
	@PutMapping
	public ResponseEntity<?> updateUser(@Valid @RequestBody UserUpdateRequest request){
		return userService.updateUser(request);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deactiveUser(@PathVariable Long userId){
		return userService.deactivateUser(userId);
	}

	
}
