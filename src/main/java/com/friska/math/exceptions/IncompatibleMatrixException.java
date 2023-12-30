package com.friska.math.exceptions;

public class IncompatibleMatrixException extends RuntimeException{
    public IncompatibleMatrixException(String msg){
        super("An error occurred trying to process a vector or matrix. " + msg);
    }
}
