package com.bassem.campaignmaster.dto;

import com.bassem.campaignmaster.model.Duration;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CampaignCreateDTO {
    @NotNull
    private String name;

    @URL
    private String url;
}