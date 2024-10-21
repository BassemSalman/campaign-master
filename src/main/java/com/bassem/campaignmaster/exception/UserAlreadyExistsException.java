package com.bassem.campaignmaster.exception;

import jakarta.persistence.EntityExistsException;

public class UserAlreadyExistsException extends EntityExistsException {
    public UserAlreadyExistsException() {
        super("User already exists");
    }
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
