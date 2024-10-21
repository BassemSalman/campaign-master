package com.bassem.campaignmaster.repository;

import java.util.List;
import java.util.Optional;

import com.bassem.campaignmaster.exception.EngagementNotFoundException;
import com.bassem.campaignmaster.exception.UserNotFoundException;
import com.bassem.campaignmaster.model.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.bassem.campaignmaster.model.Campaign;
import com.bassem.campaignmaster.model.Engagement;

@Repository
public interface EngagementRepository extends ListCrudRepository<Engagement, Long> {
	public List<Engagement> findAllByCampaign(Campaign campaign);
	public List<Engagement> findAllByUser(User user);
	public Optional<Engagement> findByPhoneToken(String phoneToken);
	public Engagement save(@Valid Engagement engagement);
	public Optional<Engagement> findByCampaignAndUser(@NotNull Campaign campaign, @NotNull User user);

	public default Engagement find(Long id){
		return this.findById(id).orElseThrow(EngagementNotFoundException::new);
	}
}
