package com.ey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.CreateEntryRequest;
import com.ey.service.EntryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/entries")
public class EntryController {

	@Autowired
	private EntryService entryService;

	@PostMapping
	public ResponseEntity<?> markEntry(@Valid @RequestBody CreateEntryRequest request) {

		return entryService.markEntry(request);
	}
}
