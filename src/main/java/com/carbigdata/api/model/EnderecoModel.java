package com.carbigdata.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {

	private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
    
}
