package com.bassem.campaignmaster.controller;

import com.bassem.campaignmaster.dto.EngagementBulkCreateDto;
import com.bassem.campaignmaster.dto.EngagementCreateDto;
import com.bassem.campaignmaster.service.EngagementService;
import com.bassem.campaignmaster.util.ApiResponseUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/engagements")
public class EngagementController {
	@Autowired
	private EngagementService service;

	@GetMapping
	public ResponseEntity<?> findAll() {
		return ApiResponseUtil.constructResponse(service.findAllDtos(), HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") long id) {
		return ApiResponseUtil.constructResponse(service.findDtoById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid EngagementCreateDto createDto) {
		return ApiResponseUtil.constructResponse(service.create(createDto), HttpStatus.CREATED);
	}
	@PostMapping("bulk")
	public ResponseEntity<?> createBulk(@RequestBody @Valid EngagementBulkCreateDto bulkCreateDto) {
		return ApiResponseUtil.constructResponse(service.bulkCreate(bulkCreateDto), HttpStatus.CREATED);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") long id) {
		return ApiResponseUtil.constructResponse(service.deleteById(id), HttpStatus.OK);
	}
	@GetMapping("{id}/url")
	public ResponseEntity<?> findUrlById(@PathVariable("id") long id) {
		return ApiResponseUtil.constructResponse(service.findUrlById(id), HttpStatus.OK);
	}
}
