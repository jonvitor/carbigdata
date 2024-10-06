CREATE TABLE cliente (
    cod_cliente SERIAL PRIMARY KEY,
    nme_cliente VARCHAR(80) NOT NULL,
   	nro_cpf varchar(11) NOT NULL,
   	data_nascimento date NOT NULL,
   	data_criacao TIMESTAMP NOT NULL
);