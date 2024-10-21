package com.bassem.campaignmaster.controller;

import com.bassem.campaignmaster.common.ApiResponseUtil;
import com.bassem.campaignmaster.dto.EngagementBulkCreateDTO;
import com.bassem.campaignmaster.dto.EngagementCreateDTO;
import com.bassem.campaignmaster.service.EngagementService;
import com.bassem.campaignmaster.service.UrlConstructionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
	@Autowired
	private UrlConstructionService urlConstructionService;

	@GetMapping
	public ResponseEntity<?> findAll() {
		return ApiResponseUtil.constructResponse(service.findAll(), HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") long id) {
		return ApiResponseUtil.constructResponse(service.findById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid EngagementCreateDTO createDTO) {
		return ApiResponseUtil.constructResponse(service.create(createDTO), HttpStatus.CREATED);
	}
	@PostMapping("bulk")
	public ResponseEntity<?> createBulk(@RequestBody @Valid EngagementBulkCreateDTO bulkCreateDTO) {
		service.bulkCreate(bulkCreateDTO);
		return ApiResponseUtil.constructResponse(null, HttpStatus.CREATED);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") long id) {
		return ApiResponseUtil.constructResponse(service.deleteById(id), HttpStatus.OK);
	}
	@GetMapping("{id}/url")
	public ResponseEntity<?> requestUrl(@PathVariable("id") long id) {
		return ApiResponseUtil.constructResponse(urlConstructionService.constructUrl(id), HttpStatus.OK);
	}
}
