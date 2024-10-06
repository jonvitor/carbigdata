package com.carbigdata.domain.model;

import java.time.OffsetDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cliente", schema = "carbigdata")
public class Cliente {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_cliente")
    private Long id;

    @Column(name = "nme_cliente", nullable = false)
    private String nome;

    @Column(name = "nro_cpf", nullable = false)
    private String cpf;

    @CreationTimestamp
	@Column(name = "data_criacao",nullable = false, columnDefinition = "timestamp")
	private OffsetDateTime dataCriacao;
    
    @Column(name = "data_nascimento",nullable = false)
	private Date dataNascimento;

}
