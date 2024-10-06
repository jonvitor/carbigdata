CREATE TABLE foto_ocorrencia (
    cod_foto_ocorrencia SERIAL PRIMARY KEY,
    cod_ocorrencia INT NOT NULL,
    data_criacao TIMESTAMP NOT NULL,
    dsc_path_bucket VARCHAR(255) NOT NULL,
    dsc_hash VARCHAR(64) NOT NULL,
    CONSTRAINT fk_ocorrencia FOREIGN KEY (cod_ocorrencia) REFERENCES ocorrencia(cod_ocorrencia)
);