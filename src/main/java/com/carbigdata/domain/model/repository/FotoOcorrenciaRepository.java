package com.carbigdata.domain.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carbigdata.domain.model.FotoOcorrencia;

@Repository
public interface FotoOcorrenciaRepository extends JpaRepository<FotoOcorrencia, Long> {
	
	List<FotoOcorrencia> findAllByOcorrencia_Id(Long ocorrenciaId);

}
