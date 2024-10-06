package com.carbigdata.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.carbigdata.api.assembler.ClienteModelAssembler;
import com.carbigdata.api.assembler.ClienteModelDisassembler;
import com.carbigdata.api.exceptionhandler.ErrorResponse;
import com.carbigdata.api.model.ClienteModel;
import com.carbigdata.api.model.input.ClienteInput;
import com.carbigdata.domain.model.Cliente;
import com.carbigdata.domain.model.exception.ClienteExistenteException;
import com.carbigdata.domain.model.repository.ClienteRepository;
import com.carbigdata.domain.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteModelAssembler clienteModelAssembler;
	
	@Autowired
	private ClienteModelDisassembler clienteModelDisassembler;
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public List<ClienteModel> listar() {
		List<Cliente> todosClientes = clienteRepository.findAll();
		
		return clienteModelAssembler.toCollectionModel(todosClientes);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteModel adicionar(@RequestBody @Valid ClienteInput clienteInput) {
		Cliente cliente = clienteModelDisassembler.toDomainObject(clienteInput);
		cliente = clienteService.salvar(cliente);
		
		return clienteModelAssembler.toModel(cliente);
	}
	
	@ExceptionHandler(ClienteExistenteException.class)
    public ResponseEntity<ErrorResponse> handleClienteExistenteException(ClienteExistenteException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}
