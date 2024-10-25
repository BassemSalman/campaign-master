package com.bassem.campaignmaster.mapper;

import com.bassem.campaignmaster.dto.CampaignCreateDto;
import com.bassem.campaignmaster.dto.CampaignResponseDto;
import com.bassem.campaignmaster.dto.CampaignUpdateDto;
import com.bassem.campaignmaster.model.Campaign;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


@Component
@Mapper(componentModel = "spring")
public abstract class CampaignMapper extends BaseMapper<Campaign, CampaignCreateDto, CampaignUpdateDto, CampaignResponseDto> {
    @Autowired
    EngagementMapper engagementMapper;

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    protected abstract void mapToResponseDto(@MappingTarget CampaignResponseDto responseDto, Campaign campaign);

    @Override
    public CampaignResponseDto toResponseDto(Campaign campaign) {
        CampaignResponseDto responseDto = new CampaignResponseDto();
        mapToResponseDto(responseDto, campaign);
        responseDto.setEngagements(engagementMapper.toResponseDtos(campaign.getEngagements()));
        return responseDto;
    }

    @Override
    public List<CampaignResponseDto> toResponseDtos(Collection<Campaign> campaigns) {
        List<CampaignResponseDto> responseDtos = new LinkedList<>();
        for (Campaign campaign : campaigns) {
            responseDtos.add(toResponseDto(campaign));
        }
        return responseDtos;
    }
}
