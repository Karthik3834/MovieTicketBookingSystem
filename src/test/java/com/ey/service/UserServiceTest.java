package com.ey.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.ey.dto.request.UserLoginRequest;
import com.ey.dto.request.UserSignupRequest;
import com.ey.entity.User;
import com.ey.enums.Role;
import com.ey.enums.UserStatus;
import com.ey.exception.InvalidCredentialsException;
import com.ey.repository.UserRepository;

@ExtendWith(MockitoExtension.class)

class UserServiceTest {

    @Mock

    private UserRepository userRepository;

    @InjectMocks

    private UserService userService;


    @Test

    void signup_shouldCreateUserSuccessfully() {

        UserSignupRequest request = new UserSignupRequest();

        request.setName("Karthik");

        request.setEmail("karthik@gmail.com");

        request.setPassword("password123");

        request.setPhone("9876543210");

        User savedUser = new User();

        savedUser.setUserId(1L);

        savedUser.setName("Karthik");

        savedUser.setEmail("karthik@gmail.com");

        savedUser.setPhone("9876543210");

        savedUser.setRole(Role.USER);

        savedUser.setStatus(UserStatus.ACTIVE);

        savedUser.setCreatedAt(LocalDateTime.now());

        when(userRepository.existsByEmail(request.getEmail()))

                .thenReturn(false);

        when(userRepository.save(org.mockito.ArgumentMatchers.any(User.class)))

                .thenReturn(savedUser);

        ResponseEntity<?> response = userService.signup(request);

        assertEquals(201, response.getStatusCodeValue());

        verify(userRepository).save(org.mockito.ArgumentMatchers.any(User.class));

    }



    @Test

    void login_shouldThrowException_whenPasswordIsInvalid() {

        UserLoginRequest request = new UserLoginRequest();

        request.setEmail("karthik@gmail.com");

        request.setPassword("wrongPassword");

        User user = new User();

        user.setEmail("karthik@gmail.com");

        user.setPassword("correctPassword");

        user.setStatus(UserStatus.ACTIVE);

        when(userRepository.findByEmail(request.getEmail()))

                .thenReturn(Optional.of(user));

        assertThrows(

                InvalidCredentialsException.class,

                () -> userService.login(request)

        );

    }



    @Test

    void deactivateUser_shouldSetStatusInactive() {

        User user = new User();

        user.setUserId(1L);

        user.setStatus(UserStatus.ACTIVE);

        when(userRepository.findById(1L))

                .thenReturn(Optional.of(user));

        ResponseEntity<String> response = userService.deactivateUser(1L);

        assertEquals("User deactivated successfully", response.getBody());
        assertEquals(UserStatus.INACTIVE,user.getStatus());
        verify(userRepository).save(user);
    }
}
