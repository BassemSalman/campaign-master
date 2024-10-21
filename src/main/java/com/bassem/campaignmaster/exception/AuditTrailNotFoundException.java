package com.bassem.campaignmaster.exception;

import jakarta.persistence.EntityNotFoundException;

public class AuditTrailNotFoundException extends EntityNotFoundException {
    public AuditTrailNotFoundException() {
        super("AuditTrail not found");
    }
    public AuditTrailNotFoundException(String message) {
        super(message);
    }
}
