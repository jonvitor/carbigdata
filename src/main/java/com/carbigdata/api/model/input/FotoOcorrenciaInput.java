package com.carbigdata.api.model.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoOcorrenciaInput {
	
	@NotBlank(message = "Hash é obrigatório")
	@NotNull(message = "Hash não pode ser vazio")
	private String dscHash;
	
}
