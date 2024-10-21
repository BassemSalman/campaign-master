package com.bassem.campaignmaster.dto;

import com.bassem.campaignmaster.model.Duration;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CampaignActivateDTO {
    @Valid
    @NotNull
    private Duration duration;
}