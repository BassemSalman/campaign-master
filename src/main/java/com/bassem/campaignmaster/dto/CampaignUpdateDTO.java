package com.bassem.campaignmaster.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

public class CampaignUpdateDTO {
    @NotNull
    @URL
    private String url;

    @NotNull
    private String name;
}
