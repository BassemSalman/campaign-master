package com.bassem.campaignmaster.service;

import com.bassem.campaignmaster.model.Campaign;
import com.bassem.campaignmaster.model.Engagement;
import com.bassem.campaignmaster.model.Metrics;
import com.bassem.campaignmaster.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@Service
public class RedirectionService {
    @Autowired
    private EngagementService engagementService;
    @Autowired
    private MetricsService metricsService;
    @Autowired
    private AuditTrailService auditTrailService;


    public RedirectView redirectToUrl(String phoneToken){
        Engagement engagement = engagementService.findByPhoneToken(phoneToken);
        Campaign campaign = engagement.getCampaign();
        User user = engagement.getUser();
        Metrics metrics = campaign.getMetrics();

        metrics.incrementTotalClicks();
        if (campaign.isActive()) {
            metrics.incrementSuccessfulClicks();
        }
        engagement.incrementClicks(); // TODO: How is it saving the engagement without save()
        metricsService.save(metrics);
        auditTrailService.save(campaign, user);
        log.debug("Redirecting user: {} to url: {} within campaign {}", user.getUsername(), campaign.getUrl(), campaign.getName());
        return new RedirectView(campaign.getUrl());
    }
}
