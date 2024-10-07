package com.carbigdata.api.model.input;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClienteInput {

	@NotBlank
	private String nome;
	
	@NotBlank
	private String cpf;
	
	@NotNull	
	private Date dataNascimento;
	
}