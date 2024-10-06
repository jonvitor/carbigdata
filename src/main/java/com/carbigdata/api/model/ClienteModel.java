package com.carbigdata.api.model;

import java.time.OffsetDateTime;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteModel {

    private Long id;
    private String nome;
    private String cpf;
	private OffsetDateTime dataCriacao;
	private Date dataNascimento;
}
