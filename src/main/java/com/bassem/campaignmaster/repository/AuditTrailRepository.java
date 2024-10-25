package com.bassem.campaignmaster.repository;

import com.bassem.campaignmaster.model.AuditTrail;
import jakarta.validation.Valid;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditTrailRepository extends ListCrudRepository<AuditTrail, Long>{
	public List<AuditTrail> findAllByCampaignId(int campaignId);
	public AuditTrail save(@Valid AuditTrail auditTrail);
}
