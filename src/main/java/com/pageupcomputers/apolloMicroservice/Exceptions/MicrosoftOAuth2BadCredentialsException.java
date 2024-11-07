package com.pageupcomputers.apolloMicroservice.Exceptions;

/**
 * Custom Exception which will be throwed when the credentials from Microsoft are incorrect
 */
public class MicrosoftOAuth2BadCredentialsException extends RuntimeException {
    public MicrosoftOAuth2BadCredentialsException(String message) {
        super(message);
    }   

    public MicrosoftOAuth2BadCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }
}
