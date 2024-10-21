package com.bassem.campaignmaster.dto;

import com.bassem.campaignmaster.model.Duration;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
public class EngagementResponseDTO {
    private Long id;
    private Long userId;
    private Long campaignId;
    private String phoneNumber;
    private String url;
    private Long clicks;
}