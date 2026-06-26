package com.ucr.planner_api.exceptions;

public class DuplicateTaskDataException extends RuntimeException {
    public DuplicateTaskDataException(String message) {
        super(message);
    }
    
}
