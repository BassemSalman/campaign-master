package com.bassem.campaignmaster.controller;

import com.bassem.campaignmaster.service.RedirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;


@RestController
@RequestMapping("/redirect")
public class RedirectionController {
	@Autowired
	private RedirectionService service;

	@GetMapping(path = "{phoneToken}")
	public RedirectView redirectToUrl(@PathVariable(name = "phoneToken") String phoneToken) {
		return service.redirectToUrl(phoneToken);
	}
}
