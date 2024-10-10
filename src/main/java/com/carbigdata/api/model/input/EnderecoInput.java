package com.carbigdata.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInput {
    
	@NotBlank(message = "Logradouro é obrigatório")
	@NotNull(message = "Logradouro não pode ser vazio")
	@Valid
    private String logradouro;

	@NotBlank(message = "Bairro é obrigatório")
	@NotNull(message = "Bairro não pode ser vazio")
	@Valid
    private String bairro;

	@NotBlank(message = "Cep é obrigatório")
	@NotNull(message = "Cep não pode ser vazio")
	@Valid
    private String cep;

	@NotBlank(message = "Cidade é obrigatório")
	@NotNull(message = "Cidade não pode ser vazio")
	@Valid
    private String cidade;

	@NotBlank(message = "Estado é obrigatório")
	@NotNull(message = "Estado não pode ser vazio")
	@Valid
    private String estado;

}
