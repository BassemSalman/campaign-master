package com.bassem.campaignmaster.dto;

import com.bassem.campaignmaster.model.Campaign;
import com.bassem.campaignmaster.model.Duration;
import com.bassem.campaignmaster.model.Engagement;
import com.bassem.campaignmaster.model.Metrics;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static java.time.LocalDateTime.now;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CampaignResponseDTO {
    private Long id;
    private String name;
    private String url;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime activationDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expiryDate;

    private List<EngagementResponseDTO> engagements;
    private Metrics metrics;

    public boolean isActive(){
        return this.activationDate != null && now().isBefore(this.getExpiryDate());
    }
}