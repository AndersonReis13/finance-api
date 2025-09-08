package com.andersonreis13.financialmanegment.exceptions.handle;

import com.andersonreis13.financialmanegment.exceptions.BadRequestException;
import com.andersonreis13.financialmanegment.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomizedExceptionHandle {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ModelFormExceptionHandle> NotFoundExceptionHandle(WebRequest request,
                                                                           NotFoundException ex){
        ModelFormExceptionHandle modelFormExceptionHandle = new ModelFormExceptionHandle(
                ex.getMessage(),
                request.getDescription(false),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(modelFormExceptionHandle, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ModelFormExceptionHandle> BadRequestExceptionHandle(WebRequest request,
                                                                            BadRequestException ex){
        ModelFormExceptionHandle modelFormExceptionHandle = new ModelFormExceptionHandle(
                ex.getMessage(),
                request.getDescription(false),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(modelFormExceptionHandle, HttpStatus.BAD_REQUEST);
    }



}
