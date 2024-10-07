package com.carbigdata.domain.model.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.carbigdata.domain.model.Endereco;

@Repository
public interface EnderecoRepository extends CustomJpaRepository<Endereco, Long>{
	
	Optional<Endereco> findByCep(String cep);

}
