package com.qa.exceptions;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/13.
 * HTTP返回码异常
 */
public class HttpStatusException extends RuntimeException {
    public HttpStatusException() {
    }

    public HttpStatusException(String message) {
        super(message);
    }
    public int status;
    public HttpStatusException(int code,String message) {
        super(message);
        this.status=code;
    }
}
