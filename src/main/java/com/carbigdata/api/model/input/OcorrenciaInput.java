package com.carbigdata.api.model.input;

import java.time.OffsetDateTime;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaInput {

	@NotNull(message = "Cliente não pode ser vazio")
	@Valid
	private ClienteInput cliente;
	
	@NotNull(message = "Endereco não pode ser vazio")
	@Valid
	private EnderecoInput endereco;
	
	@NotNull(message = "Data da ocorrencia não pode ser vazia")
	private OffsetDateTime dataOcorrencia;
	
	@NotNull(message = "Foto da ocorrencia não pode ser vazia")
	private FotoOcorrenciaInput fotoOcorrencia;
}
