package com.carbigdata.domain.model.exception;


public class OcorrenciaInexistenteException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

	public OcorrenciaInexistenteException(String message) {
        super(message);
    }
}