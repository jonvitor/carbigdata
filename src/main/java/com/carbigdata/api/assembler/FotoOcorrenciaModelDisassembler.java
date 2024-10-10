package com.carbigdata.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carbigdata.api.model.input.FotoOcorrenciaInput;
import com.carbigdata.domain.model.FotoOcorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class FotoOcorrenciaModelDisassembler {

	private ModelMapper modelMapper;
	
	public FotoOcorrencia toDomainObject(FotoOcorrenciaInput ocorrenciaInput) {
		return modelMapper.map(ocorrenciaInput, FotoOcorrencia.class);
	}
	
	public void copyToDomainObject(FotoOcorrenciaInput ocorrenciaInput, FotoOcorrencia ocorrencia) {
		modelMapper.map(ocorrenciaInput, ocorrencia);
	}
}
