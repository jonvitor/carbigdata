package com.carbigdata.api.model.input;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClienteInput {

	@NotBlank(message = "Nome do cliente é obrigatório")
	@NotNull(message = "Nome do cliente não pode ser vazio")
	private String nome;
	
	@NotBlank(message = "CPF do cliente é obrigatório")
	@NotNull(message = "CPF do cliente não pode ser vazio")
	@Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos")
	private String cpf;
	
	@NotNull(message = "Data de nascimento não pode ser vazia")
	private Date dataNascimento;
	
}