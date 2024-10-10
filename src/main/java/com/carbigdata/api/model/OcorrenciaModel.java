package com.carbigdata.api.model;

import java.time.OffsetDateTime;
import java.util.List;

import com.carbigdata.domain.model.StatusOcorrencia;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaModel {

	private ClienteModel cliente;
	private EnderecoModel endereco;
	private OffsetDateTime dataOcorrencia;
	private StatusOcorrencia staOcorrencia;
	private List<FotoOcorrenciaModel> fotosOcorrencia;
	
}
