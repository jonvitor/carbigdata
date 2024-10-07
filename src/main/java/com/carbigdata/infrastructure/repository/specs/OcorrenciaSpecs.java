package com.carbigdata.infrastructure.repository.specs;

import java.util.ArrayList;

import org.springframework.data.jpa.domain.Specification;

import com.carbigdata.domain.filter.OcorrenciaFilter;
import com.carbigdata.domain.model.Ocorrencia;

import jakarta.persistence.criteria.Predicate;

public class OcorrenciaSpecs {
	public static Specification<Ocorrencia> usandoFiltro(OcorrenciaFilter filtro) {
		return (root, query, builder) -> {
			var predicates = new ArrayList<Predicate>();

			if (filtro.getClienteId() != null) {
				predicates.add(builder.equal(root.get("cliente").get("id"), filtro.getClienteId()));
			}

			if (filtro.getCpf() != null) {
				predicates.add(builder.equal(root.get("cliente").get("cpf"), filtro.getCpf()));
			}
			
			if (filtro.getNome() != null) {
				predicates.add(builder.equal(root.get("cliente").get("nome"), filtro.getNome()));
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
