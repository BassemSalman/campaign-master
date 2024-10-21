package com.bassem.campaignmaster.exception;

import jakarta.persistence.EntityNotFoundException;

public class EngagementNotFoundException extends EntityNotFoundException {
    public EngagementNotFoundException() {
        super("Engagement not found");
    }
    public EngagementNotFoundException(String message) {
        super(message);
    }
}
