package com.spb.kbv.catcallm.infrastructure;

public class APIError {
    private int code;
    private String message;

    public APIError(){}

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
