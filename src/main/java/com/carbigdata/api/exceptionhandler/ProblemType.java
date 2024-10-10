package com.carbigdata.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"), 
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");

	private String title;
	private String uri;

	ProblemType(String path, String title) {
		this.uri = "https://carbigdata.com.br" + path;
		this.title = title;
	}
}
