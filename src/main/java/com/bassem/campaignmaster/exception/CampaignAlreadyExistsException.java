package com.bassem.campaignmaster.exception;

import jakarta.persistence.EntityExistsException;

public class CampaignAlreadyExistsException extends EntityExistsException {
    public CampaignAlreadyExistsException() {
        super("Campaign already exists");
    }
    public CampaignAlreadyExistsException(String message) {
        super(message);
    }
}
