package com.carbigdata.api.model.input;

import java.time.OffsetDateTime;

import com.carbigdata.domain.model.Endereco;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaInput {

	@NotNull
	@Valid
	private ClienteInput cliente;
	
	@NotNull
	@Valid
	private Endereco endereco;
	
	@NotNull
	private OffsetDateTime dataOcorrencia;
	
	@NotNull
	private FotoOcorrenciaInput fotoOcorrencia;
}
