package com.ey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.UserLoginRequest;
import com.ey.dto.request.UserSignupRequest;
import com.ey.dto.request.UserUpdateRequest;
import com.ey.dto.response.UserResponse;
import com.ey.entity.User;
import com.ey.enums.UserStatus;
import com.ey.exception.DuplicateUserException;
import com.ey.exception.InvalidCredentialsException;
import com.ey.exception.UserDeactivatedException;
import com.ey.exception.UserNotFoundException;
import com.ey.mapper.UserMapper;
import com.ey.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<UserResponse> signup(UserSignupRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateUserException("Email already registered");
        }

        User user = UserMapper.toEntity(request);
        User savedUser = userRepository.save(user);

        return new ResponseEntity<>(
                UserMapper.toResponse(savedUser),
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<UserResponse> login(UserLoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException("Invalid email or password"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        if (user.getStatus() == UserStatus.INACTIVE) {
            throw new UserDeactivatedException("User account is deactivated");
        }

        return ResponseEntity.ok(UserMapper.toResponse(user));
    }

    public ResponseEntity<UserResponse> getUserById(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id: " + userId));

        return ResponseEntity.ok(UserMapper.toResponse(user));
    }

    public ResponseEntity<UserResponse> updateUser(UserUpdateRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id: " + request.getUserId()));

        UserMapper.updateEntity(user, request);
        User updatedUser = userRepository.save(user);

        return ResponseEntity.ok(UserMapper.toResponse(updatedUser));
    }

    public ResponseEntity<String> deactivateUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id: " + userId));

        user.setStatus(UserStatus.INACTIVE);
        userRepository.save(user);

        return ResponseEntity.ok("User deactivated successfully");
    }
}
