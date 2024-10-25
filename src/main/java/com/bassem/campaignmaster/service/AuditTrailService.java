package com.bassem.campaignmaster.service;

import com.bassem.campaignmaster.exception.AuditTrailNotFoundException;
import com.bassem.campaignmaster.model.AuditTrail;
import com.bassem.campaignmaster.model.Campaign;
import com.bassem.campaignmaster.model.User;
import com.bassem.campaignmaster.repository.AuditTrailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuditTrailService  {
	@Autowired
	private AuditTrailRepository repo;

	public List<AuditTrail> findAll() {
		return repo.findAll();
	}

	public AuditTrail findById(long id) {
		return repo.findById(id).orElseThrow(AuditTrailNotFoundException::new);
	}

	public List<AuditTrail> findAllByCampaignId(int campaignId) {
		return repo.findAllByCampaignId(campaignId);
	}

	// Only called from services
	public AuditTrail save(Campaign campaign, User user) {
		AuditTrail auditTrail = AuditTrail.builder()
				.campaignId(campaign.getId())
				.userId(user.getId())
				.phoneNumber(user.getPhoneNumber())
				.build();
		return repo.save(auditTrail);
	}
}
