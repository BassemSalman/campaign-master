package com.bassem.campaignmaster.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class CampaignUpdateDto {
    @NotBlank
    @URL
    private String url;

    @NotBlank
    private String name;
}
