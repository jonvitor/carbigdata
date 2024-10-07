package com.carbigdata.api.model;

import java.time.OffsetDateTime;

import com.carbigdata.domain.model.Cliente;
import com.carbigdata.domain.model.Endereco;
import com.carbigdata.domain.model.StatusOcorrencia;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaModel {

	private Cliente cliente;
	private Endereco endereco;
	private OffsetDateTime dataOcorrencia;
	private String status;
}
