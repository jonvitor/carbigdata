package com.carbigdata.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carbigdata.domain.model.Cliente;
import com.carbigdata.domain.model.exception.ClienteExistenteException;
import com.carbigdata.domain.model.repository.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Transactional
	public Cliente salvar(Cliente cliente) {	
		Optional<Cliente> clienteExistente = clienteRepository.findByCpf(cliente.getCpf());
		
		if (clienteExistente.isPresent()) {
			throw new ClienteExistenteException("Já existe cliente com o cpf cadastrado");
		}
		
		return clienteRepository.save(cliente);
	}
}
