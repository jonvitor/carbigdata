package com.carbigdata.domain.model.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.carbigdata.domain.model.Cliente;

@Repository
public interface ClienteRepository extends CustomJpaRepository<Cliente, Long>{

	Optional<Cliente> findByCpf(String cpf);
}
