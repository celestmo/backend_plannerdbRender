package com.ucr.planner_api.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ucr.planner_api.Dtos.ErrorDto;
import com.ucr.planner_api.exceptions.CourseNotFoundException;
import com.ucr.planner_api.exceptions.DuplicateCourseDataException;
import com.ucr.planner_api.exceptions.DuplicateTaskDataException;
import com.ucr.planner_api.exceptions.DuplicateUserDataException;
import com.ucr.planner_api.exceptions.InvalidCourseDataException;
import com.ucr.planner_api.exceptions.InvalidUserDataException;
import com.ucr.planner_api.exceptions.TaskNotFoundException;
import com.ucr.planner_api.exceptions.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDto> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto(404, ex.getMessage()));
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorDto> handleTaskNotFoundException(TaskNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto(404, ex.getMessage()));
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ErrorDto> handleCourseNotFoundException(CourseNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto(404, ex.getMessage()));
    }

    @ExceptionHandler(DuplicateUserDataException.class)
    public ResponseEntity<ErrorDto> handleDuplicateUserDataException(DuplicateUserDataException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDto(409, ex.getMessage()));
    }

    @ExceptionHandler(DuplicateCourseDataException.class)
    public ResponseEntity<ErrorDto> handleDuplicateCourseDataException(DuplicateCourseDataException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDto(409, ex.getMessage()));
    }

    @ExceptionHandler(DuplicateTaskDataException.class)
    public ResponseEntity<ErrorDto> handleDuplicateTaskDataException(DuplicateTaskDataException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDto(409, ex.getMessage()));
    }

    @ExceptionHandler(InvalidCourseDataException.class)
    public ResponseEntity<ErrorDto> handleInvalidCourseDataException(InvalidCourseDataException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(400, ex.getMessage()));
    }

    @ExceptionHandler(InvalidUserDataException.class)
    public ResponseEntity<ErrorDto> handleInvalidUserDataException(InvalidUserDataException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(400, ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleValidationErrors(MethodArgumentNotValidException ex) {
        String mensaje = ex.getBindingResult().getFieldErrors()
            .stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .findFirst()
            .orElse("Datos inválidos");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(400, mensaje));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGeneralError(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorDto(500, "Error interno del servidor"));
    }
}
