package com.bassem.campaignmaster.exception;

import jakarta.persistence.EntityExistsException;

public class EngagementAlreadyExistsException extends EntityExistsException {
    public EngagementAlreadyExistsException() {
        super("Engagement already exists");
    }
    public EngagementAlreadyExistsException(String message) {
        super(message);
    }
}
