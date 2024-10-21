package com.bassem.campaignmaster.controller;

import com.bassem.campaignmaster.common.ApiResponseUtil;
import com.bassem.campaignmaster.dto.CampaignActivateDTO;
import com.bassem.campaignmaster.dto.CampaignCreateDTO;
import com.bassem.campaignmaster.dto.CampaignUpdateDTO;
import com.bassem.campaignmaster.service.CampaignService;
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
		return ApiResponseUtil.constructResponse(service.findAll(), HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") long id) {
		return ApiResponseUtil.constructResponse(service.findById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid CampaignCreateDTO createDTO) {
		return ApiResponseUtil.constructResponse(service.create(createDTO), HttpStatus.CREATED);
	}

	@PatchMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody @Valid CampaignUpdateDTO updateDTO) {
		return ApiResponseUtil.constructResponse(service.update(id, updateDTO), HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		return ApiResponseUtil.constructResponse(service.deleteById(id), HttpStatus.OK);
	}

	@PostMapping("/activation/{id}")
	public ResponseEntity<?> activate(@PathVariable("id") long id, @RequestBody @Valid CampaignActivateDTO activateDTO) {
		return ApiResponseUtil.constructResponse(service.activate(id, activateDTO), HttpStatus.CREATED);
	}

	@DeleteMapping("/activation/{id}")
	public ResponseEntity<?> deactivate(@PathVariable("id") long id) {
		return ApiResponseUtil.constructResponse(service.deactivate(id), HttpStatus.OK);
	}

	@GetMapping("{id}/engagements")
	public ResponseEntity<?> findAllEngagements(@PathVariable("id") long id) {
		return ApiResponseUtil.constructResponse(service.findAllEngagements(id), HttpStatus.OK);
	}
}
