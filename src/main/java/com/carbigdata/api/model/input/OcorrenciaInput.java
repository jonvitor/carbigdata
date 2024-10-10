package com.carbigdata.api.model.input;

import java.time.OffsetDateTime;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaInput {

	@NotNull(message = "Cliente não pode ser vazio")
	@Valid
	private ClienteInput cliente;
	
	@NotNull
	@Valid
	private EnderecoInput endereco;
	
	@NotNull
	private OffsetDateTime dataOcorrencia;
	
	@NotNull
	private FotoOcorrenciaInput fotoOcorrencia;
}
