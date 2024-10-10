package com.carbigdata.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carbigdata.domain.model.FotoOcorrencia;
import com.carbigdata.domain.model.Ocorrencia;
import com.carbigdata.domain.model.StatusOcorrencia;
import com.carbigdata.domain.model.exception.OcorrenciaInexistenteException;
import com.carbigdata.domain.model.repository.OcorrenciaRepository;
import com.carbigdata.infrastructure.service.S3FotoStorageService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class OcorrenciaService {

	private OcorrenciaRepository ocorrenciaRepository;
	private ClienteService clienteService;
	private EnderecoService enderecoService;
	private S3FotoStorageService fotoStorageService;
	
	@Transactional
	public Ocorrencia salvar(Ocorrencia ocorrencia, FotoOcorrencia fotoOcorrencia, String nomeFoto) {	
		var cliente = ocorrencia.getCliente();
		var endereco = ocorrencia.getEndereco();

		cliente = clienteService.salvarOuAtualizarCliente(cliente);
		endereco = enderecoService.salvarOuAtualizarEndereco(endereco);
		
		ocorrencia.setCliente(cliente);
		ocorrencia.setEndereco(endereco);
		ocorrencia.setStaOcorrencia(StatusOcorrencia.ATIVA);
		
		Ocorrencia ocorrenciaSalva = ocorrenciaRepository.save(ocorrencia);
		
		try {
			fotoStorageService.enviarFotoOcorrencia(nomeFoto, fotoOcorrencia, ocorrenciaSalva.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ocorrenciaSalva;
	}
	
	@Transactional
	public void finalizar(Long id) {
		var ocorrenciaOpt = encontrarOcorrencia(id);
		
		if (ocorrenciaOpt.isEmpty()) {
			throw new OcorrenciaInexistenteException();
		}
		var ocorrencia = ocorrenciaOpt.get();
		
		if (ocorrencia.getStaOcorrencia().getDescricao().equals("Ativa")) {
			ocorrencia.setStaOcorrencia(StatusOcorrencia.FINALIZADA);
			ocorrenciaRepository.save(ocorrencia);
		}

	} 
	
	public Optional<Ocorrencia> encontrarOcorrencia(Long id) {
		return ocorrenciaRepository.findById(id);
	}
}
