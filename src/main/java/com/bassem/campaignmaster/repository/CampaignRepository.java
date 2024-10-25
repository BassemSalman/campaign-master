package com.bassem.campaignmaster.repository;

import com.bassem.campaignmaster.model.Campaign;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CampaignRepository extends ListCrudRepository<Campaign, Long>{
	public Campaign save(@Valid Campaign campaign);
	public Optional<Campaign> findByName(@NotNull String name);

}
