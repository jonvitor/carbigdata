package com.carbigdata.api.assembler;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carbigdata.api.model.OcorrenciaModel;
import com.carbigdata.domain.model.Ocorrencia;


@Component
public class OcorrenciaModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public OcorrenciaModel toModel(Ocorrencia ocorrencia) {
		return modelMapper.map(ocorrencia, OcorrenciaModel.class);
	}
	
	public List<OcorrenciaModel> toCollectionModel(Collection<Ocorrencia> ocorrencias) {
		return ocorrencias.stream()
				.map(ocorrencia -> toModel(ocorrencia))
				.collect(Collectors.toList());
	}
	
}