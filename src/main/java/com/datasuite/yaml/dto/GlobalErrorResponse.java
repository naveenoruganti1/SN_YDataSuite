package com.datasuite.yaml.dto;

public class GlobalErrorResponse {

    private String message;

    public GlobalErrorResponse() {
    }

    public GlobalErrorResponse(String message) {
        this.message = message;
    }

    // Getters and setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
