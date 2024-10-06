package com.carbigdata.domain.model.exception;


public class ClienteExistenteException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

	public ClienteExistenteException(String message) {
        super(message);
    }
}