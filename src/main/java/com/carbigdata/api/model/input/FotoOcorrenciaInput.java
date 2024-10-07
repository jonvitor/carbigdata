package com.carbigdata.api.model.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoOcorrenciaInput {

	@NotNull
	@NotBlank
	private String nomeFoto;
	
	@NotNull
	@NotBlank
	private String dscHash;
	
}
