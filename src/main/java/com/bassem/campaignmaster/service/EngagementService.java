package com.bassem.campaignmaster.service;

import com.bassem.campaignmaster.dto.EngagementBulkCreateDto;
import com.bassem.campaignmaster.dto.EngagementCreateDto;
import com.bassem.campaignmaster.dto.EngagementResponseDto;
import com.bassem.campaignmaster.exception.CampaignActiveException;
import com.bassem.campaignmaster.exception.CampaignInactiveException;
import com.bassem.campaignmaster.exception.EngagementAlreadyExistsException;
import com.bassem.campaignmaster.exception.EngagementNotFoundException;
import com.bassem.campaignmaster.mapper.EngagementMapper;
import com.bassem.campaignmaster.model.Campaign;
import com.bassem.campaignmaster.model.Engagement;
import com.bassem.campaignmaster.model.User;
import com.bassem.campaignmaster.repository.EngagementRepository;
import com.bassem.campaignmaster.util.UrlConstructionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class EngagementService  {
	@Autowired
	private EngagementRepository repo;
	@Autowired
	private CampaignService campaignService;
	@Autowired
	private UserService userService;
	@Autowired
	private EngagementMapper mapper;
	@Autowired
	private UrlConstructionUtil urlConstructionUtil;

	public List<EngagementResponseDto> findAllDtos() {
		return mapper.toResponseDtos(repo.findAll());
	}

	public Engagement findById(long id){
		return repo.findById(id).orElseThrow(EngagementNotFoundException::new);
	}
	public EngagementResponseDto findDtoById(long id) {
		return mapper.toResponseDto(this.findById(id));
	}

	public String findUrlById(long id) {
		Engagement engagement = this.findById(id);
		Campaign campaign = engagement.getCampaign();
		if(!campaign.isActive())
			throw new CampaignInactiveException();
		return urlConstructionUtil.constructUrl(engagement.getPhoneToken());
	}

	public EngagementResponseDto create(EngagementCreateDto createDto){
		Campaign campaign = campaignService.findById(createDto.getCampaignId());
		if(campaign.isActive())
			throw new CampaignActiveException();
		User user = userService.findById(createDto.getUserId());
		if(repo.findByCampaignAndUser(campaign, user).isPresent())
			throw new EngagementAlreadyExistsException();
		log.debug("Creating engagement for campaign id:  {} and user id:  {}", campaign.getId(), user.getId());
		return mapper.toResponseDto(repo.save(
				Engagement.builder().
				campaign(campaign)
				.user(user)
				.clicks(0L)
				.build())
		);
	}

	public List<EngagementResponseDto> bulkCreate(EngagementBulkCreateDto bulkCreateDto){
		Campaign campaign = campaignService.findById(bulkCreateDto.getCampaignId());
		if(campaign.isActive())
			throw new CampaignActiveException();
		List<Engagement> engagements = new LinkedList<>();
		User user;
		for(Long userId : bulkCreateDto.getUserIds()) {
			user = userService.findById(userId);
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
		return mapper.toResponseDtos(repo.saveAll(engagements));
	}

	public Engagement findByPhoneToken(String phoneToken) {
		return repo.findByPhoneToken(phoneToken).orElseThrow(() -> new EngagementNotFoundException());
	}
	public EngagementResponseDto deleteById(long id) {
		Engagement engagement = this.findById(id);
		repo.delete(engagement);
		log.debug("Deleting engagement id:  {}", engagement.getId());
		return mapper.toResponseDto(engagement);
	}
}
