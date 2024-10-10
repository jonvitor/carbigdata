package com.carbigdata.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carbigdata.domain.model.FotoOcorrencia;
import com.carbigdata.domain.model.repository.FotoOcorrenciaRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class FotoOcorrenciaService {

	private FotoOcorrenciaRepository fotoOcorrenciaRepository;
	
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
