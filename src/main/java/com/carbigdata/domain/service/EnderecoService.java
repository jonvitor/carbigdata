package com.carbigdata.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carbigdata.domain.model.Endereco;
import com.carbigdata.domain.model.repository.EnderecoRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class EnderecoService {

	private EnderecoRepository enderecoRepository;
	
	@Transactional
	public Endereco salvarOuAtualizarEndereco(Endereco endereco) {
        return enderecoRepository.findByCep(endereco.getCep())
            .map(enderecoExistente -> {
                endereco.setId(enderecoExistente.getId());
                return enderecoRepository.save(endereco);
            })
            .orElseGet(() -> enderecoRepository.save(endereco));
    }
}
