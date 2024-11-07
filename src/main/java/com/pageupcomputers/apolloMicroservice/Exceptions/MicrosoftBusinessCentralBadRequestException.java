package com.pageupcomputers.apolloMicroservice.Exceptions;

/**
 * Custom Exception which will be throwed when the Business Central API throws an Bad Request error
 */
public class MicrosoftBusinessCentralBadRequestException extends RuntimeException {
    public MicrosoftBusinessCentralBadRequestException(String message) {
        super(message);
    }   

    public MicrosoftBusinessCentralBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
