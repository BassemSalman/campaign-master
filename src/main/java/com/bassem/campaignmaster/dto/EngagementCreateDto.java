package com.bassem.campaignmaster.dto;

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
public class EngagementCreateDto {
    @NotNull
    private Long campaignId;

    @NotNull
    private Long userId;
}