package com.bassem.campaignmaster.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CampaignCreateDto {
    @NotBlank
    private String name;

    @NotBlank
    @URL
    private String url;
}