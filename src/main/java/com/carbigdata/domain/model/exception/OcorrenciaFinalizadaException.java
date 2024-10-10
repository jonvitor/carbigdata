package com.carbigdata.domain.model.exception;

public class OcorrenciaFinalizadaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OcorrenciaFinalizadaException() {
		super("Ocorrência já finalizada!");
	}
	
	public OcorrenciaFinalizadaException(String mensagem) {
		super(mensagem);
	}

}
