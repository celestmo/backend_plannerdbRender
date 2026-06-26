package com.ucr.planner_api.exceptions;

public class DuplicateCourseDataException extends RuntimeException {
    public DuplicateCourseDataException(String message) {
        super(message);
    }
}
