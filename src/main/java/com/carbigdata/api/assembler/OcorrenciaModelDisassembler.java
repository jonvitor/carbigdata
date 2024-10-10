package com.carbigdata.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carbigdata.api.model.input.OcorrenciaInput;
import com.carbigdata.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class OcorrenciaModelDisassembler {

	private ModelMapper modelMapper;
	
	public Ocorrencia toDomainObject(OcorrenciaInput ocorrenciaInput) {
		return modelMapper.map(ocorrenciaInput, Ocorrencia.class);
	}
	
	public void copyToDomainObject(OcorrenciaInput ocorrenciaInput, Ocorrencia ocorrencia) {
		modelMapper.map(ocorrenciaInput, ocorrencia);
	}
}
