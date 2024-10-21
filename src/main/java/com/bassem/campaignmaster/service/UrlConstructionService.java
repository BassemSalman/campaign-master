package com.bassem.campaignmaster.service;

import com.bassem.campaignmaster.model.Engagement;
import com.bassem.campaignmaster.model.User;
import com.bassem.campaignmaster.repository.EngagementRepository;
import io.swagger.v3.oas.models.links.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bassem.campaignmaster.model.Campaign;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


//service that implements logic of requesting url and saving token
@Service
public class UrlConstructionService {
	@Autowired
	private EngagementRepository engagementRepo;
	@Autowired
	private PhoneTokenGenerationService phoneTokenGenerationService;

	@Value("${base.url}")
	private String baseUrl;

	@Value("${server.servlet.context-path}")
	private String contextPath;

	public String constructUrl(Long engagementId) {
		Engagement engagement = engagementRepo.find(engagementId);
		return this.constructUrl(engagement);
	}

	public String constructUrl(Engagement engagement) {
		if(engagement.getPhoneToken() == null)
			return null;
		return this.constructUrl(engagement.getPhoneToken());
	}
	public String constructUrl(String phoneToken) {
		return "" + baseUrl + contextPath + "/redirect/" + phoneToken;
	}
}

