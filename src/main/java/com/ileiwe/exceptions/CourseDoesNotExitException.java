package com.ileiwe.exceptions;

public class CourseDoesNotExitException extends RuntimeException {
    public CourseDoesNotExitException(String message) {
        super(message);
    }
}
