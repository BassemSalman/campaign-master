package com.bassem.campaignmaster.service;

import com.bassem.campaignmaster.dto.EngagementBulkCreateDTO;
import com.bassem.campaignmaster.dto.EngagementCreateDTO;
import com.bassem.campaignmaster.dto.EngagementResponseDTO;
import com.bassem.campaignmaster.exception.EngagementAlreadyExistsException;
import com.bassem.campaignmaster.exception.EngagementNotFoundException;
import com.bassem.campaignmaster.mapper.EngagementMapper;
import com.bassem.campaignmaster.model.Campaign;
import com.bassem.campaignmaster.model.Engagement;
import com.bassem.campaignmaster.model.User;
import com.bassem.campaignmaster.repository.CampaignRepository;
import com.bassem.campaignmaster.repository.EngagementRepository;
import com.bassem.campaignmaster.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class EngagementService  {
	@Autowired
	private EngagementRepository repo;
	@Autowired
	private CampaignRepository campaignRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private EngagementMapper mapper;

	public List<EngagementResponseDTO> findAll() {
		return mapper.toResponseDTOs(repo.findAll());
	}

	public EngagementResponseDTO findById(long id) {
		return mapper.toResponseDTO(repo.find(id));
	}

	public EngagementResponseDTO create(EngagementCreateDTO createDTO){
		Campaign campaign = campaignRepo.find(createDTO.getCampaignId());
		User user = userRepo.find(createDTO.getUserId());
		if(repo.findByCampaignAndUser(campaign, user).isPresent())
			throw new EngagementAlreadyExistsException();
		log.debug("Creating engagement for campaign id:  {} and user id:  {}", campaign.getId(), user.getId());
		return mapper.toResponseDTO(repo.save(
				Engagement.builder().
				campaign(campaign)
				.user(user)
				.clicks(0L)
				.build())
		);
	}

	public List<EngagementResponseDTO> bulkCreate(EngagementBulkCreateDTO bulkCreateDTO){
		List<Engagement> engagements = new LinkedList<>();
		User user;
		Campaign campaign = campaignRepo.find(bulkCreateDTO.getCampaignId());
		for(Long userId : bulkCreateDTO.getUserIds()) {
			user = userRepo.find(userId);
			if(repo.findByCampaignAndUser(campaign, user).isPresent())
				throw new EngagementAlreadyExistsException();
			engagements.add(
					Engagement.builder()
							.user(user)
							.campaign(campaign)
							.clicks(0L)
							.build()
			);
		}
		log.debug("Bulk creating engagements for campaign id:  {}", campaign.getId());
		return mapper.toResponseDTOs(repo.saveAll(engagements));
	}

	public Engagement findByPhoneToken(String phoneToken) {
		return repo.findByPhoneToken(phoneToken).orElseThrow(() -> new EngagementNotFoundException());
	}
	public EngagementResponseDTO deleteById(long id) {
		Engagement engagement = repo.find(id);
		repo.delete(engagement);
		log.debug("Deleting engagement id:  {}", engagement.getId());
		return mapper.toResponseDTO(engagement);
	}
}
