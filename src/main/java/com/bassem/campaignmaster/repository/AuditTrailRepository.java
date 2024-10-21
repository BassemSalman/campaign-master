package com.bassem.campaignmaster.repository;

import java.util.List;
import java.util.Optional;

import com.bassem.campaignmaster.exception.AuditTrailNotFoundException;
import com.bassem.campaignmaster.exception.UserNotFoundException;
import com.bassem.campaignmaster.model.User;
import jakarta.validation.Valid;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.bassem.campaignmaster.model.AuditTrail;

@Repository
public interface AuditTrailRepository extends ListCrudRepository<AuditTrail, Long>{
	public List<AuditTrail> findAllByCampaignId(int campaignId);
	public AuditTrail save(@Valid AuditTrail auditTrail);
	public default AuditTrail find(Long id){
		return this.findById(id).orElseThrow(AuditTrailNotFoundException::new);
	}
}
