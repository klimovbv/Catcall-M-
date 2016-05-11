package com.spb.kbv.catcallm.infrastructure;

public class ApiError {
    private int code;
    private String message;

    public ApiError(){}

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
