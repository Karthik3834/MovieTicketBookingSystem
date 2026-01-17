package com.ey.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserUpdateRequest {
	
	@NotNull(message = "User ID Cannot be Empty")
	private Long userId;
	
	@Size(min=2,message="Name must have atleast 2 characters")
	private String name;
	
	@Pattern(regexp = "^[6-9][0-9]{9}$",message = "Phone number must be 10 digits")
	private String phone;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

}
