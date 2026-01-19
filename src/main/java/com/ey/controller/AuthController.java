package com.ey.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.UserLoginRequest;
import com.ey.dto.request.UserSignupRequest;
import com.ey.dto.response.AuthResponse;
import com.ey.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserSignupRequest request) {

		authService.register(request);
		return ResponseEntity.ok(Map.of("message", "User registered successfully"));
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody UserLoginRequest request) {

		return ResponseEntity.ok(authService.login(request));
	}

	@PostMapping("/forgot-password")
	public ResponseEntity<?> forgotPassword(@RequestParam String email, @RequestParam String newPassword) {

		authService.forgotPassword(email, newPassword);
		return ResponseEntity.ok(Map.of("message", "Password updated successfully"));
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout() {

		authService.logout();
		return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
	}
}
