package com.bassem.campaignmaster.service;

import com.bassem.campaignmaster.dto.CampaignActivateDTO;
import com.bassem.campaignmaster.dto.CampaignCreateDTO;
import com.bassem.campaignmaster.dto.CampaignResponseDTO;
import com.bassem.campaignmaster.dto.CampaignUpdateDTO;
import com.bassem.campaignmaster.dto.EngagementResponseDTO;
import com.bassem.campaignmaster.exception.CampaignActiveException;
import com.bassem.campaignmaster.exception.CampaignAlreadyExistsException;
import com.bassem.campaignmaster.exception.CampaignInactiveException;
import com.bassem.campaignmaster.exception.CampaignNotFoundException;
import com.bassem.campaignmaster.exception.EngagementAlreadyExistsException;
import com.bassem.campaignmaster.mapper.CampaignMapper;
import com.bassem.campaignmaster.mapper.EngagementMapper;
import com.bassem.campaignmaster.model.Campaign;
import com.bassem.campaignmaster.model.Metrics;
import com.bassem.campaignmaster.model.Engagement;
import com.bassem.campaignmaster.repository.CampaignRepository;
import com.bassem.campaignmaster.repository.EngagementRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class CampaignService  {
	@Autowired
	private CampaignRepository repo;
	@Autowired
	private EngagementRepository engagementRepo;
	@Autowired
	private CampaignMapper mapper;
	@Autowired
	private PhoneTokenGenerationService phoneTokenGenerationService;
	@Autowired
	private UrlConstructionService urlConstructionService;
	@Autowired
	private EngagementMapper engagementMapper;

	public List<CampaignResponseDTO> findAll() {
		return mapper.toResponseDTOs(repo.findAll());
	}

	public List<EngagementResponseDTO> findAllEngagements(long id) {
		Campaign campaign = repo.find(id);
		return engagementMapper.toResponseDTOs(campaign.getEngagements());
	}

	public CampaignResponseDTO findById(long id) {
		return mapper.toResponseDTO(repo.find(id));
	}


	public CampaignResponseDTO create(@Valid CampaignCreateDTO createDTO) {
		if(repo.findByName(createDTO.getName()).isPresent())
			throw new CampaignAlreadyExistsException();
		Campaign campaign = mapper.fromCreateDTO(createDTO);
		Metrics metrics = new Metrics();
		campaign.setMetrics(metrics);
		metrics.setMetricsCampaign(campaign);
		log.debug("Creating campaign with name {}", campaign.getName());
		return mapper.toResponseDTO(repo.save(campaign));
	}

	public CampaignResponseDTO update(long id, @Valid CampaignUpdateDTO updateDTO) {
		Campaign campaign = repo.find(id);
		return mapper.toResponseDTO(repo.save(mapper.fromUpdateDTO(campaign, updateDTO)));
	}

	@Transactional
	public CampaignResponseDTO activate(long id, @Valid CampaignActivateDTO activateDTO) {
		Campaign campaign = repo.find(id);
		if(campaign.isActive())
			throw new CampaignActiveException();
		phoneTokenGenerationService.bulkGeneratePhoneTokens(campaign);
		campaign.activate(activateDTO.getDuration());
		log.debug("Activating campaign id:  {}", campaign.getId());
		return mapper.toResponseDTO(repo.save(campaign));
	}
	public CampaignResponseDTO deactivate(long id) {
		Campaign campaign = repo.find(id);
		if(!campaign.isActive())
			throw new CampaignInactiveException();
		campaign.deactivate();
		log.debug("Deactivating campaign id:  {}", campaign.getId());
		return mapper.toResponseDTO(repo.save(campaign));
	}

	// deletes referenced engagements & metrics
	public CampaignResponseDTO deleteById(long id) {
		Campaign campaign = repo.find(id);
		repo.delete(campaign);
		log.debug("Deleting campaign id:  {}", campaign.getId());
		return mapper.toResponseDTO(campaign);
	}


}
