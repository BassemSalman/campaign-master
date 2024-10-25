package com.bassem.campaignmaster.service;

import com.bassem.campaignmaster.dto.CampaignActivateDto;
import com.bassem.campaignmaster.dto.CampaignCreateDto;
import com.bassem.campaignmaster.dto.CampaignResponseDto;
import com.bassem.campaignmaster.dto.CampaignUpdateDto;
import com.bassem.campaignmaster.dto.EngagementResponseDto;
import com.bassem.campaignmaster.exception.CampaignActiveException;
import com.bassem.campaignmaster.exception.CampaignAlreadyExistsException;
import com.bassem.campaignmaster.exception.CampaignInactiveException;
import com.bassem.campaignmaster.exception.CampaignNotFoundException;
import com.bassem.campaignmaster.mapper.CampaignMapper;
import com.bassem.campaignmaster.mapper.EngagementMapper;
import com.bassem.campaignmaster.model.Campaign;
import com.bassem.campaignmaster.model.Metrics;
import com.bassem.campaignmaster.repository.CampaignRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CampaignService  {
	@Autowired
	private CampaignRepository repo;
	@Autowired
	private CampaignMapper mapper;
	@Autowired
	private PhoneTokenGenerationService phoneTokenGenerationService;
	@Autowired
	private EngagementMapper engagementMapper;

	public List<CampaignResponseDto> findAllDtos() {
		return mapper.toResponseDtos(repo.findAll());
	}

	public List<EngagementResponseDto> findAllEngagementDtos(long id) {
		Campaign campaign = this.findById(id);
		return engagementMapper.toResponseDtos(campaign.getEngagements());
	}

	public Campaign findById(long id){
		return repo.findById(id).orElseThrow(CampaignNotFoundException::new);
	}
	
	public CampaignResponseDto findDtoById(long id) {
		return mapper.toResponseDto(this.findById(id));
	}


	public CampaignResponseDto create(@Valid CampaignCreateDto createDto) {
		if(repo.findByName(createDto.getName()).isPresent())
			throw new CampaignAlreadyExistsException();
		Campaign campaign = mapper.fromCreateDto(createDto);
		Metrics metrics = new Metrics();
		campaign.setMetrics(metrics);
		metrics.setMetricsCampaign(campaign);
		log.debug("Creating campaign with name {}", campaign.getName());
		return mapper.toResponseDto(repo.save(campaign));
	}

	public CampaignResponseDto update(long id, @Valid CampaignUpdateDto updateDto) {
		Campaign campaign = this.findById(id);
		return mapper.toResponseDto(repo.save(mapper.fromUpdateDto(campaign, updateDto)));
	}

	@Transactional
	public CampaignResponseDto activate(long id, @Valid CampaignActivateDto activateDto) {
		Campaign campaign = this.findById(id);
		if(campaign.isActive())
			throw new CampaignActiveException();
		phoneTokenGenerationService.bulkGeneratePhoneTokens(campaign);
		campaign.activate(activateDto.getDuration());
		log.debug("Activating campaign id:  {}", campaign.getId());
		return mapper.toResponseDto(repo.save(campaign));
	}

	public CampaignResponseDto deactivate(long id) {
		Campaign campaign = this.findById(id);
		if(!campaign.isActive())
			throw new CampaignInactiveException();
		campaign.deactivate();
		log.debug("Deactivating campaign id:  {}", campaign.getId());
		return mapper.toResponseDto(repo.save(campaign));
	}

	// deletes referenced engagements & metrics
	public CampaignResponseDto deleteById(long id) {
		Campaign campaign = this.findById(id);
		repo.delete(campaign);
		log.debug("Deleting campaign id:  {}", campaign.getId());
		return mapper.toResponseDto(campaign);
	}


}
