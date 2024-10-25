package com.bassem.campaignmaster.mapper;

import com.bassem.campaignmaster.dto.EngagementCreateDto;
import com.bassem.campaignmaster.dto.EngagementResponseDto;
import com.bassem.campaignmaster.model.Engagement;
import com.bassem.campaignmaster.util.UrlConstructionUtil;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class EngagementMapper extends BaseMapper<Engagement, EngagementCreateDto, Void, EngagementResponseDto> {
    @Autowired
    UrlConstructionUtil urlConstructionUtil;

    private EngagementResponseDto toResponseDtoCustom(Engagement engagement) {
        return EngagementResponseDto.builder()
                .id(engagement.getId())
                .userId(engagement.getUser().getId())
                .phoneNumber(engagement.getUser().getPhoneNumber())
                .campaignId(engagement.getCampaign().getId())
                .clicks(engagement.getClicks())
                .build();
    }

    @Override
    public EngagementResponseDto toResponseDto(Engagement engagement) {
        EngagementResponseDto responseDto = this.toResponseDtoCustom(engagement);
        String constructedUrl = urlConstructionUtil.constructUrl(engagement.getPhoneToken());
        responseDto.setUrl(constructedUrl);
        return responseDto;
    }

    @Override
    public List<EngagementResponseDto> toResponseDtos(Collection<Engagement> engagements) {
        List<EngagementResponseDto> responseDtos = new LinkedList<>();
        if(engagements == null)
            return new LinkedList<>();
        for(Engagement engagement: engagements){
            responseDtos.add(toResponseDto(engagement));
        }
        return responseDtos;
    }
}
