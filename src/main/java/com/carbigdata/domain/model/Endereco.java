package com.carbigdata.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "endereco", schema = "carbigdata")
public class Endereco {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_endereco")
    private Long id;

    @Column(name = "nme_logradouro", nullable = false)
	@NotBlank
    private String logradouro;

    @Column(name = "nme_bairro", nullable = false)
	@NotBlank
    private String bairro;

    @Column(name = "nro_cep", nullable = false)
	@NotBlank
    private String cep;

    @Column(name = "nme_cidade", nullable = false)
	@NotBlank
    private String cidade;

    @Column(name = "nme_estado", nullable = false)
	@NotBlank
    private String estado;

}
