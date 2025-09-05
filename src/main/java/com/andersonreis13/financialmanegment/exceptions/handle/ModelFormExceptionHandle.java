package com.andersonreis13.financialmanegment.exceptions.handle;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ModelFormExceptionHandle {
    private String msg;
    private String path;
    private Integer httpStatus;
    private HttpStatus status;
    private LocalDateTime localDateTime = LocalDateTime.now();

    public ModelFormExceptionHandle(String msg, String path, Integer httpStatus, HttpStatus status) {
        this.msg = msg;
        this.path = path;
        this.httpStatus = httpStatus;
        this.status = status;
        this.localDateTime = LocalDateTime.now();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
