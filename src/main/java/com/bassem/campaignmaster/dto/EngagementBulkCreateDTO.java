package com.bassem.campaignmaster.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder

@NoArgsConstructor
@AllArgsConstructor
public class EngagementBulkCreateDTO {
    @NotNull
    private Long campaignId;

    @NotNull
    @NotEmpty
    private Long[] userIds;
}
