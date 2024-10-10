package com.carbigdata.api.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteModel {

    private String nome;
    private String cpf;
	private Date dataNascimento;
}
