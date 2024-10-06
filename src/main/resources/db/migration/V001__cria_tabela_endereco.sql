CREATE TABLE endereco (
    cod_endereco SERIAL PRIMARY KEY,
    nme_logradouro VARCHAR(255) NOT NULL,
    nme_bairro VARCHAR(100) NOT NULL,
    nro_cep VARCHAR(20) NOT NULL,
    nme_cidade VARCHAR(100) NOT NULL,
    nme_estado VARCHAR(50) NOT NULL
);