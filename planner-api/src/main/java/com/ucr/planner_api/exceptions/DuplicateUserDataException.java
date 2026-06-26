package com.ucr.planner_api.exceptions;

public class DuplicateUserDataException  extends RuntimeException {
    public DuplicateUserDataException(String message) {
        super(message);
    }
    
}
