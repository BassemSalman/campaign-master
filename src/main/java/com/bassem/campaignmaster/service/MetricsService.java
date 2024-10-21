package com.bassem.campaignmaster.service;

import com.bassem.campaignmaster.exception.MetricsNotFoundException;
import com.bassem.campaignmaster.model.Campaign;
import com.bassem.campaignmaster.model.Metrics;
import com.bassem.campaignmaster.repository.CampaignRepository;
import com.bassem.campaignmaster.repository.MetricsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class MetricsService {
	@Autowired
	private MetricsRepository metricsRepo;
	@Autowired
	private CampaignRepository campaignRepo;

	public Metrics findByCampaignId(long id) {
		Campaign campaign = campaignRepo.find(id);
		return metricsRepo.findByMetricsCampaign(campaign).orElseThrow(() -> new MetricsNotFoundException());
	}

	public Metrics findByMetricsCampaign(Campaign campaign) {
		return metricsRepo.findByMetricsCampaign(campaign).orElseThrow(() -> new MetricsNotFoundException());
	}
	public Metrics save(Metrics metrics) {
		return metricsRepo.save(metrics);
	}
}
