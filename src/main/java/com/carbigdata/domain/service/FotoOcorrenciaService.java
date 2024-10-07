package com.carbigdata.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carbigdata.domain.model.FotoOcorrencia;
import com.carbigdata.domain.model.Ocorrencia;
import com.carbigdata.domain.model.StatusOcorrencia;
import com.carbigdata.domain.model.repository.FotoOcorrenciaRepository;
import com.carbigdata.domain.model.repository.OcorrenciaRepository;

import jakarta.transaction.Transactional;

@Service
public class FotoOcorrenciaService {

	@Autowired
	private OcorrenciaRepository ocorrenciaRepository;
	
	@Autowired
	private FotoOcorrenciaRepository fotoOcorrenciaRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Transactional
	public FotoOcorrencia salvar(FotoOcorrencia fotoOcorrencia) {	

		try {
			return fotoOcorrenciaRepository.save(fotoOcorrencia);
		} catch (Exception e) {
			System.out.println(e);
		}
		return fotoOcorrenciaRepository.save(fotoOcorrencia);
	}
}
