package com.bassem.campaignmaster.exception;

import jakarta.persistence.EntityNotFoundException;

public class CampaignNotFoundException extends EntityNotFoundException {
    public CampaignNotFoundException() {
        super("Campaign not found");
    }
    public CampaignNotFoundException(String message) {
        super(message);
    }
}
