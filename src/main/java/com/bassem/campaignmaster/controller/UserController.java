package com.bassem.campaignmaster.controller;

import com.bassem.campaignmaster.common.ApiResponseUtil;
import com.bassem.campaignmaster.dto.UserDTO;
import com.bassem.campaignmaster.service.UserService;
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
@RequestMapping("/users")

public class UserController {
	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<?> findAll() {
		return ApiResponseUtil.constructResponse(service.findAll(), HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") long id) {
		return ApiResponseUtil.constructResponse(service.findById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid UserDTO createDTO) {
		return ApiResponseUtil.constructResponse(service.create(createDTO), HttpStatus.CREATED);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		return ApiResponseUtil.constructResponse(service.deleteById(id), HttpStatus.OK);
	}
}
