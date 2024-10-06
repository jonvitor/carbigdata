package com.carbigdata.api.model.input;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClienteInput {

	@NotBlank
	private String nome;
	
	@NotBlank
	private String cpf;
	
	@NotBlank	
	private Date dataNascimento;
	
}