package com.carbigdata.domain.model;

public enum StatusOcorrencia {

	ATIVA("Ativa"),
	FINALIZADA("Finalizada");
	
	private String descricao;
	
	StatusOcorrencia(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
}