package com.example.poll.response;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException{
    private ApiResponseCode status;
    private String message;

    public ApiException(ApiResponseCode status, Exception e) {
        super(e);
        this.status = status;
        this.message = status.getValue();
    }

    public ApiException(ApiResponseCode status, String message, Exception e) {
        super(e);
        this.status = status;
        this.message = message;
    }

    public ApiException(ApiResponseCode status) {
        this.status = status;
        this.message = status.getValue();
    }

    public ApiException(ApiResponseCode status, String message) {
        this.status = status;
        this.message = message;
    }
}
