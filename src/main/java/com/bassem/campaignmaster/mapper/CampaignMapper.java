package com.bassem.campaignmaster.mapper;

import com.bassem.campaignmaster.dto.CampaignActivateDTO;
import com.bassem.campaignmaster.dto.CampaignCreateDTO;
import com.bassem.campaignmaster.dto.CampaignResponseDTO;
import com.bassem.campaignmaster.dto.CampaignUpdateDTO;
import com.bassem.campaignmaster.dto.CampaignResponseDTO;
import com.bassem.campaignmaster.model.Campaign;
import com.bassem.campaignmaster.model.Engagement;
import com.bassem.campaignmaster.service.UrlConstructionService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


@Component
@Mapper(componentModel = "spring")

public abstract class CampaignMapper implements BaseMapper<Campaign, CampaignCreateDTO, CampaignUpdateDTO, Void> {

    @Autowired
    EngagementMapper engagementMapper;

    public abstract CampaignResponseDTO toResponseDTONative(Campaign campaign);

    public CampaignResponseDTO toResponseDTO(Campaign campaign) {
        CampaignResponseDTO responseDTO = this.toResponseDTONative(campaign);
        responseDTO.setEngagements(engagementMapper.toResponseDTOs(campaign.getEngagements()));
        return responseDTO;
    }

    public List<CampaignResponseDTO> toResponseDTOs(Collection<Campaign> campaigns){
        List<CampaignResponseDTO> responseDTOs = new LinkedList<>();
        for(Campaign campaign : campaigns){
            responseDTOs.add(toResponseDTO(campaign));
        }
        return responseDTOs;
    }
}
