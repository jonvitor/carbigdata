package com.carbigdata.infrastructure.repository.specs;

import java.util.ArrayList;

import org.springframework.data.jpa.domain.Specification;

import com.carbigdata.domain.filter.OcorrenciaFilter;
import com.carbigdata.domain.model.Ocorrencia;

import jakarta.persistence.criteria.Predicate;

public class OcorrenciaSpecs {
	
	private static final String CLIENTE = "cliente";
	
	public static Specification<Ocorrencia> usandoFiltro(OcorrenciaFilter filtro) {
		return (root, query, builder) -> {
			var predicates = new ArrayList<Predicate>();

			if (filtro.getClienteId() != null) {
				predicates.add(builder.equal(root.get(CLIENTE).get("id"), filtro.getClienteId()));
			}

			if (filtro.getCpf() != null) {
				predicates.add(builder.equal(root.get(CLIENTE).get("cpf"), filtro.getCpf()));
			}
			
			if (filtro.getNome() != null) {
				predicates.add(builder.equal(root.get(CLIENTE).get("nome"), filtro.getNome()));
			}
			
			if (filtro.getCidade() != null) {
				predicates.add(builder.equal(root.get("endereco").get("cidade"), filtro.getCidade()));
			}
			
			if (filtro.getDataOcorrencia() != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("dataOcorrencia"), 
						filtro.getDataOcorrencia()));
			}

			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
