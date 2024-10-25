package com.bassem.campaignmaster.exception;

public class CampaignInactiveException extends IllegalStateException {
    public CampaignInactiveException() {
        super("Campaign is inactive");
    }
    public CampaignInactiveException(String message) {
        super(message);
    }
}
