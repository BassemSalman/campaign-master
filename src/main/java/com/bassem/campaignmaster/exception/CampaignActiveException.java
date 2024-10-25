package com.bassem.campaignmaster.exception;

public class CampaignActiveException extends IllegalStateException {
    public CampaignActiveException() {
        super("Campaign is active");
    }
    public CampaignActiveException(String message) {
        super(message);
    }
}
