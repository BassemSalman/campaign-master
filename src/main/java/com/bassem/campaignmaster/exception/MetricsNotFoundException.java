package com.bassem.campaignmaster.exception;

import jakarta.persistence.EntityNotFoundException;

public class MetricsNotFoundException extends EntityNotFoundException {
    public MetricsNotFoundException() {
        super("Metrics not found");
    }
    public MetricsNotFoundException(String message) {
        super(message);
    }
}
