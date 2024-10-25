package com.bassem.campaignmaster.controller;

import com.bassem.campaignmaster.dto.CampaignActivateDto;
import com.bassem.campaignmaster.dto.CampaignCreateDto;
import com.bassem.campaignmaster.dto.CampaignUpdateDto;
import com.bassem.campaignmaster.service.CampaignService;
import com.bassem.campaignmaster.util.ApiResponseUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/campaigns")

public class CampaignController {
	@Autowired
	private CampaignService service;

	@GetMapping
	public ResponseEntity<?> findAll() {
		return ApiResponseUtil.constructResponse(service.findAllDtos(), HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") long id) {
		return ApiResponseUtil.constructResponse(service.findDtoById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid CampaignCreateDto createDto) {
		return ApiResponseUtil.constructResponse(service.create(createDto), HttpStatus.CREATED);
	}

	@PatchMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody @Valid CampaignUpdateDto updateDto) {
		return ApiResponseUtil.constructResponse(service.update(id, updateDto), HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		return ApiResponseUtil.constructResponse(service.deleteById(id), HttpStatus.OK);
	}

	@PostMapping("/activation/{id}")
	public ResponseEntity<?> activate(@PathVariable("id") long id, @RequestBody @Valid CampaignActivateDto activateDto) {
		return ApiResponseUtil.constructResponse(service.activate(id, activateDto), HttpStatus.CREATED);
	}

	@DeleteMapping("/activation/{id}")
	public ResponseEntity<?> deactivate(@PathVariable("id") long id) {
		return ApiResponseUtil.constructResponse(service.deactivate(id), HttpStatus.OK);
	}

	@GetMapping("{id}/engagements")
	public ResponseEntity<?> findAllEngagements(@PathVariable("id") long id) {
		return ApiResponseUtil.constructResponse(service.findAllEngagementDtos(id), HttpStatus.OK);
	}
}
