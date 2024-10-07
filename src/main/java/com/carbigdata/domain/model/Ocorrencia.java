package com.carbigdata.domain.model;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ocorrencia", schema = "carbigdata")
public class Ocorrencia {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_ocorrencia")
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "cod_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "cod_endereco", nullable = false)
    private Endereco endereco;

    @Column(name = "data_ocorrencia", nullable = false)
    private OffsetDateTime dataOcorrencia;

    @Column(name = "sta_ocorrencia", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusOcorrencia staOcorrencia;
}
