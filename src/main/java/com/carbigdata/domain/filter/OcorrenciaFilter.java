package com.carbigdata.domain.filter;

import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaFilter {

	private Long clienteId;
	private String cpf;
	private String nome;
	private String cidade;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime dataOcorrencia;
}
