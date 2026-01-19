package com.ey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.CancelBookingRequest;
import com.ey.service.CancellationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cancel")
public class CancellationController {

	@Autowired
	private CancellationService cancellationService;

	@PostMapping
	public ResponseEntity<?> cancelBooking(@Valid @RequestBody CancelBookingRequest request) {

		return cancellationService.cancelBooking(request);
	}
}
