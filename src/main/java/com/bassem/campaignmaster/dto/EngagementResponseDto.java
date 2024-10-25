package com.bassem.campaignmaster.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EngagementResponseDto {
    private Long id;
    private Long userId;
    private Long campaignId;
    private String phoneNumber;
    private String url;
    private Long clicks;
}