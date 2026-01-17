package com.ey.dto.request;

import com.ey.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserSignupRequest {
	
	@NotBlank(message = "Name is required to proceed")
	@Size(min=2,message="Name must have atleast 2 characters")
	private String name;
	
	@Email(message="Invalid Email format")
	@NotBlank(message = "Email is required")
	private String email;
	
	@NotBlank(message="Password is required")
	@Size(min=6, message = "Password must be atleast 6 characters")
	private String password;
	
	@Pattern(regexp = "^[6-9][0-9]{9}$",message = "Phone number must be 10 digits and start with 6,7,8,9")
	private String phone;
	
	private Role role;

	public UserSignupRequest() {
		super();
	}

	public UserSignupRequest(
			@NotBlank(message = "Name is required to proceed") @Size(min = 2, message = "Name must have atleast 2 characters") String name,
			@Email(message = "Invalid Email format") @NotBlank(message = "Email is required") String email,
			@NotBlank(message = "Password is required") @Size(min = 6, message = "Password must be atleast 6 characters") String password,
			@Pattern(regexp = "^[6-9][0-9]{9}$", message = "Phone number must be 10 digits and start with 6,7,8,9") String phone,
			Role role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}

