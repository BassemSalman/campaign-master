package com.bassem.campaignmaster.repository;

import java.util.List;
import java.util.Optional;

import com.bassem.campaignmaster.exception.CampaignNotFoundException;
import com.bassem.campaignmaster.exception.UserNotFoundException;
import com.bassem.campaignmaster.model.AuditTrail;
import com.bassem.campaignmaster.model.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.bassem.campaignmaster.model.Campaign;

@Repository
public interface CampaignRepository extends ListCrudRepository<Campaign, Long>{
	public Campaign save(@Valid Campaign campaign);
	public Optional<Campaign> findByName(@NotNull String name);
	public default Campaign find(Long id){
		return this.findById(id).orElseThrow(CampaignNotFoundException::new);
	}
}
