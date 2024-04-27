package com.att.tdp.bisbis10.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmptyNameException extends ResponseStatusException {
    public EmptyNameException() {
        super(HttpStatus.BAD_REQUEST,"name can not be empty");
    }
}
