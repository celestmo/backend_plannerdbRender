package com.ucr.planner_api.exceptions;

public class InvalidUserDataException extends RuntimeException{
     public InvalidUserDataException(String message) {
        super(message);
    }
}
