package com.carbigdata.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carbigdata.domain.model.Ocorrencia;
import com.carbigdata.domain.model.StatusOcorrencia;
import com.carbigdata.domain.model.repository.EnderecoRepository;
import com.carbigdata.domain.model.repository.OcorrenciaRepository;

import jakarta.transaction.Transactional;

@Service
public class OcorrenciaService {

	@Autowired
	private OcorrenciaRepository ocorrenciaRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Transactional
	public Ocorrencia salvar(Ocorrencia ocorrencia) {	
		var cliente = ocorrencia.getCliente();
		var endereco = ocorrencia.getEndereco();

		cliente = clienteService.salvarOuAtualizarCliente(cliente);
		endereco = enderecoService.salvarOuAtualizarEndereco(endereco);
		
		ocorrencia.setCliente(cliente);
		ocorrencia.setEndereco(endereco);
		ocorrencia.setStaOcorrencia(StatusOcorrencia.ATIVA);
		
		return ocorrenciaRepository.save(ocorrencia);
	}
}
