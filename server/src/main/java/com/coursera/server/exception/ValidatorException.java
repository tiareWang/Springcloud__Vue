package com.coursera.server.exception;

public class ValidatorException extends RuntimeException{  //继承runtime异常
    public ValidatorException(String message){
        super(message);
    }

}
