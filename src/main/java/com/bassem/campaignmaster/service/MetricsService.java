package com.bassem.campaignmaster.service;

import com.bassem.campaignmaster.exception.MetricsNotFoundException;
import com.bassem.campaignmaster.model.Campaign;
import com.bassem.campaignmaster.model.Metrics;
import com.bassem.campaignmaster.repository.MetricsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetricsService {
	@Autowired
	private MetricsRepository repo;

	public Metrics findByMetricsCampaign(Campaign campaign) {
		return repo.findByMetricsCampaign(campaign).orElseThrow(MetricsNotFoundException::new);
	}

	public Metrics save(Metrics metrics) {
		return repo.save(metrics);
	}
}
