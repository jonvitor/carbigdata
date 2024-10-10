package com.carbigdata.api.assembler;


import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carbigdata.api.model.FotoOcorrenciaModel;
import com.carbigdata.domain.model.FotoOcorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class FotoOcorrenciaModelAssembler {

	private ModelMapper modelMapper;
	
	public FotoOcorrenciaModel toModel(FotoOcorrencia ocorrencia) {
		return modelMapper.map(ocorrencia, FotoOcorrenciaModel.class);
	}
	
	public List<FotoOcorrenciaModel> toCollectionModel(Collection<FotoOcorrencia> fotosOcorrencia) {
		return fotosOcorrencia.stream()
				.map(this::toModel)
				.toList();
	}
	
}