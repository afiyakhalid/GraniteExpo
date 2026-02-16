package com.graniteexpo.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 1. ADD THIS ANNOTATION (So Spring returns 409 Conflict)
@ResponseStatus(HttpStatus.CONFLICT)
// 2. CRITICAL: IT MUST EXTEND RUNTIMEEXCEPTION
public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);
    }
}