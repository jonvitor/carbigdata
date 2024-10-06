package com.carbigdata.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
@Table(name = "endereco", schema = "carbigdata")
public class Endereco {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_endereco")
    private Long id;

    @Column(name = "nme_logradouro", nullable = false)
    private String logradouro;

    @Column(name = "nme_bairro", nullable = false)
    private String bairro;

    @Column(name = "nro_cep", nullable = false)
    private String cep;

    @Column(name = "nme_cidade", nullable = false)
    private String cidade;

    @Column(name = "nme_estado", nullable = false)
    private String estado;

    public String deomonho() {
    	return this.estado;
    }
}
