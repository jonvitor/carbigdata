package com.carbigdata.api.model;

import java.time.OffsetDateTime;
import java.util.List;

import com.carbigdata.domain.model.Cliente;
import com.carbigdata.domain.model.Endereco;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaModel {

	private Cliente cliente;
	private Endereco endereco;
	private OffsetDateTime dataOcorrencia;
	private String status;
	List<FotoOcorrenciaModel> fotosOcorrencia;
	
}
