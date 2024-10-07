package com.carbigdata.api.model.input;

import java.time.OffsetDateTime;

import com.carbigdata.domain.model.Endereco;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaInput {

	@NotBlank
	private ClienteInput cliente;
	
	@NotBlank
	private Endereco endereco;
	
	@NotBlank
	private OffsetDateTime dataOcorrencia;
}
