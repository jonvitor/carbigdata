package com.carbigdata.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.carbigdata.api.assembler.FotoOcorrenciaModelDisassembler;
import com.carbigdata.api.exceptionhandler.ErrorResponse;
import com.carbigdata.api.model.input.FotoOcorrenciaInput;
import com.carbigdata.domain.model.FotoOcorrencia;
import com.carbigdata.domain.model.exception.OcorrenciaInexistenteException;
import com.carbigdata.domain.model.repository.FotoOcorrenciaRepository;
import com.carbigdata.infrastructure.service.S3FotoStorageService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/ocorrencias/foto/{ocorrenciaId}")
@Validated
public class FotoOcorrenciaController {

	@Autowired
	private FotoOcorrenciaRepository fotoOcorrenciaRepository;
	
	@Autowired
	private S3FotoStorageService fotoStorageService;
	
	@Autowired
	private FotoOcorrenciaModelDisassembler modelDisassembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void adicionarFoto(@PathVariable Long ocorrenciaId, @RequestBody @Valid FotoOcorrenciaInput fotoOcorrenciaInput) {
		FotoOcorrencia fotoOcorrencia = modelDisassembler.toDomainObject(fotoOcorrenciaInput);
		
		fotoStorageService.enviarFotoOcorrencia(fotoOcorrenciaInput.getNomeFoto(), fotoOcorrencia, ocorrenciaId);
	}
	
	@ExceptionHandler(OcorrenciaInexistenteException.class)
    public ResponseEntity<ErrorResponse> handleClienteExistenteException(OcorrenciaInexistenteException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
