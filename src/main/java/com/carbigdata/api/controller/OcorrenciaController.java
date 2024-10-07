package com.carbigdata.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
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
import com.carbigdata.domain.filter.OcorrenciaFilter;
import com.carbigdata.domain.model.FotoOcorrencia;
import com.carbigdata.domain.model.Ocorrencia;
import com.carbigdata.domain.model.repository.FotoOcorrenciaRepository;
import com.carbigdata.domain.model.repository.OcorrenciaRepository;
import com.carbigdata.domain.service.OcorrenciaService;
import com.carbigdata.infrastructure.repository.specs.OcorrenciaSpecs;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/ocorrencias")
@Validated
public class OcorrenciaController {

	@Autowired
	private OcorrenciaRepository ocorrenciaRepository;
	
	@Autowired
	private FotoOcorrenciaRepository fotoOcorrenciaRepository;
	
	@Autowired
	private OcorrenciaModelAssembler ocorrenciaModelAssembler;
	
	@Autowired
	private OcorrenciaModelDisassembler ocorrenciaModelDisassembler;
	
	@Autowired
	private OcorrenciaService ocorrenciaService;
	
	@Autowired
	private FotoOcorrenciaModelDisassembler fotoOcorrenciaModelDisassembler;
	
	 @Autowired
	 private PagedResourcesAssembler<OcorrenciaModel> pagedResourcesAssembler;
	
	@GetMapping
	public PagedModel<EntityModel<OcorrenciaModel>> pesquisar(OcorrenciaFilter filtro, 
			@PageableDefault(size = 2) Pageable pageable) {
		Page<Ocorrencia> todasOcorrencias = ocorrenciaRepository.findAll(
				OcorrenciaSpecs.usandoFiltro(filtro), pageable);
		
		for (Ocorrencia ocorrencia : todasOcorrencias) {
			List<FotoOcorrencia> fotosOcorrencia = fotoOcorrenciaRepository.findAllByOcorrencia_Id(ocorrencia.getId());
			
			ocorrencia.setFotosOcorrencia(fotosOcorrencia);
		}
		
		List<OcorrenciaModel> ocorrenciasModel = ocorrenciaModelAssembler.toCollectionModel(todasOcorrencias.getContent());
		
		Page<OcorrenciaModel> ocorrenciasModelPage = new PageImpl<>(
				ocorrenciasModel, pageable, todasOcorrencias.getTotalElements());
		
		return pagedResourcesAssembler.toModel(ocorrenciasModelPage);

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
