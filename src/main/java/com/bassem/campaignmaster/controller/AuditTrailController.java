package com.bassem.campaignmaster.controller;

import com.bassem.campaignmaster.service.AuditTrailService;
import com.bassem.campaignmaster.util.ApiResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/audits")

public class AuditTrailController {
	@Autowired
	private AuditTrailService service;

	@GetMapping
	public ResponseEntity<?> findAll() {
		return ApiResponseUtil.constructResponse(service.findAll(), HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") long id) {
		return ApiResponseUtil.constructResponse(service.findById(id), HttpStatus.OK);
	}

	@GetMapping("campaigns/{campaignId}")
	public ResponseEntity<?> findAllByCampaignId(@PathVariable("campaignId") int campaignId) {
		return ApiResponseUtil.constructResponse(service.findAllByCampaignId(campaignId), HttpStatus.OK);
	}
}
