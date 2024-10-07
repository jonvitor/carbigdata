package com.carbigdata.api.model;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import com.carbigdata.domain.model.Cliente;
import com.carbigdata.domain.model.Endereco;
import com.carbigdata.domain.model.Ocorrencia;
import com.carbigdata.domain.model.StatusOcorrencia;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoOcorrenciaModel {

    private Ocorrencia ocorrencia;
    private LocalDateTime dataCriacao;
    private String dscPathBucket;
    private String dscHash;

}
