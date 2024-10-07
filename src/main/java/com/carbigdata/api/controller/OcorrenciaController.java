package com.carbigdata.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.carbigdata.api.assembler.FotoOcorrenciaModelDisassembler;
import com.carbigdata.api.assembler.OcorrenciaModelAssembler;
import com.carbigdata.api.assembler.OcorrenciaModelDisassembler;
import com.carbigdata.api.model.OcorrenciaModel;
import com.carbigdata.api.model.input.FotoOcorrenciaInput;
import com.carbigdata.api.model.input.OcorrenciaInput;
import com.carbigdata.domain.model.FotoOcorrencia;
import com.carbigdata.domain.model.Ocorrencia;
import com.carbigdata.domain.model.repository.OcorrenciaRepository;
import com.carbigdata.domain.service.OcorrenciaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/ocorrencias")
@Validated
public class OcorrenciaController {

	@Autowired
	private OcorrenciaRepository ocorrenciaRepository;
	
	@Autowired
	private OcorrenciaModelAssembler ocorrenciaModelAssembler;
	
	@Autowired
	private OcorrenciaModelDisassembler ocorrenciaModelDisassembler;
	
	@Autowired
	private OcorrenciaService ocorrenciaService;
	
	@Autowired
	private FotoOcorrenciaModelDisassembler fotoOcorrenciaModelDisassembler;
	
	@GetMapping
	public List<OcorrenciaModel> listar() {
		List<Ocorrencia> todasOcorrencias = ocorrenciaRepository.findAll();
		
		return ocorrenciaModelAssembler.toCollectionModel(todasOcorrencias);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaModel adicionar(@Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
		FotoOcorrenciaInput fotoOcorrenciaInput = ocorrenciaInput.getFotoOcorrencia();	
		Ocorrencia ocorrencia = ocorrenciaModelDisassembler.toDomainObject(ocorrenciaInput);
		FotoOcorrencia fotoOcorrencia = fotoOcorrenciaModelDisassembler.toDomainObject(fotoOcorrenciaInput); 
		
		ocorrencia = ocorrenciaService.salvar(ocorrencia, fotoOcorrencia, fotoOcorrenciaInput.getNomeFoto());
		
		return ocorrenciaModelAssembler.toModel(ocorrencia);
	}
	
}
