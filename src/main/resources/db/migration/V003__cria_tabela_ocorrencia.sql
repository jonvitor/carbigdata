CREATE TABLE ocorrencia (
    cod_ocorrencia SERIAL PRIMARY KEY,
    cod_cliente INT NOT NULL,
    cod_endereco INT NOT NULL,
    data_ocorrencia TIMESTAMP NOT NULL,
    sta_ocorrencia VARCHAR(10) NOT NULL,
    CONSTRAINT fk_cliente FOREIGN KEY (cod_cliente) REFERENCES cliente(cod_cliente),
    CONSTRAINT fk_endereco FOREIGN KEY (cod_endereco) REFERENCES endereco(cod_endereco)
);