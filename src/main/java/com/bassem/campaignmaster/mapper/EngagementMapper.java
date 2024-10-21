package com.bassem.campaignmaster.mapper;

import com.bassem.campaignmaster.dto.EngagementCreateDTO;
import com.bassem.campaignmaster.dto.EngagementResponseDTO;
import com.bassem.campaignmaster.model.Engagement;
import com.bassem.campaignmaster.service.UrlConstructionService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Component
@Mapper(componentModel = "spring")

public abstract class EngagementMapper implements BaseMapper<Engagement, EngagementCreateDTO, Void, Void> {
    @Autowired
    UrlConstructionService urlConstructionService;

    private EngagementResponseDTO toResponseDTONative(Engagement engagement) {
        return EngagementResponseDTO.builder()
                .id(engagement.getId())
                .userId(engagement.getUser().getId())
                .phoneNumber(engagement.getUser().getPhoneNumber())
                .campaignId(engagement.getCampaign().getId())
                .clicks(engagement.getClicks())
                .build();
    }

    public EngagementResponseDTO toResponseDTO(Engagement engagement) {
        EngagementResponseDTO responseDTO = this.toResponseDTONative(engagement);
        String constructedUrl = urlConstructionService.constructUrl(engagement);
        responseDTO.setUrl(constructedUrl);
        return responseDTO;
    }

    public List<EngagementResponseDTO> toResponseDTOs(Collection<Engagement> engagements) {
        List<EngagementResponseDTO> responseDTOs = new LinkedList<>();
        if(engagements == null)
            return new LinkedList<>();
        for(Engagement engagement : engagements){
            responseDTOs.add(toResponseDTO(engagement));
        }
        return responseDTOs;
    }
}
