package com.bassem.campaignmaster.util;

import com.bassem.campaignmaster.service.PhoneTokenGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


//service that implements logic of requesting url and saving token
@Service
public class UrlConstructionUtil {
	@Autowired
	private PhoneTokenGenerationService phoneTokenGenerationService;

	@Value("${base.url}")
	private String baseUrl;

	@Value("${server.servlet.context-path}")
	private String contextPath;

	public String constructUrl(String phoneToken) {
		if(phoneToken == null)
			return null;
		return "" + baseUrl + contextPath + "/redirect/" + phoneToken;
	}
}

