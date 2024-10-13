TRUNCATE TABLE ocorrencia, foto_ocorrencia, cliente, endereco RESTART IDENTITY CASCADE;

-- Populando a tabela 'endereco'
INSERT INTO endereco (nme_logradouro, nme_bairro, nro_cep, nme_cidade, nme_estado)
VALUES 
    ('Rua das Flores', 'Centro', '12345-678', 'São Paulo', 'SP'),
    ('Avenida Brasil', 'Jardim América', '98765-432', 'Rio de Janeiro', 'RJ'),
    ('Rua Afonso Pena', 'Santa Efigênia', '34567-890', 'Belo Horizonte', 'MG'),
    ('Avenida Paulista', 'Bela Vista', '76543-210', 'São Paulo', 'SP'),
    ('Rua Marechal Deodoro', 'Vila Nova', '11111-111', 'Curitiba', 'PR'),
    ('Avenida Independência', 'Centro', '22222-222', 'Porto Alegre', 'RS'),
    ('Rua Sete de Setembro', 'Copacabana', '33333-333', 'Rio de Janeiro', 'RJ'),
    ('Avenida Beira Mar', 'Meireles', '44444-444', 'Fortaleza', 'CE'),
    ('Rua das Palmeiras', 'Lagoa', '55555-555', 'Florianópolis', 'SC');

-- Populando a tabela 'cliente'
INSERT INTO cliente (nme_cliente, nro_cpf, data_nascimento, data_criacao)
VALUES 
    ('João Silva', '12345678901', '1985-01-15', CURRENT_TIMESTAMP),
    ('Maria Oliveira', '98765432100', '1990-07-22', CURRENT_TIMESTAMP),
    ('Pedro Costa', '45678912345', '1975-03-10', CURRENT_TIMESTAMP),
    ('Ana Souza', '65432198765', '1988-12-05', CURRENT_TIMESTAMP),
    ('Carlos Alberto', '23456789012', '1982-05-30', CURRENT_TIMESTAMP),
    ('Beatriz Lima', '87654321098', '1995-11-09', CURRENT_TIMESTAMP),
    ('Fernanda Torres', '34567890123', '1978-08-22', CURRENT_TIMESTAMP),
    ('Gustavo Martins', '76543210987', '1992-02-17', CURRENT_TIMESTAMP),
    ('Rafael Nascimento', '98765431234', '1987-10-03', CURRENT_TIMESTAMP);

-- Populando a tabela 'ocorrencia'
INSERT INTO ocorrencia (cod_cliente, cod_endereco, data_ocorrencia, sta_ocorrencia)
VALUES 
    (1, 1, CURRENT_TIMESTAMP, 'ATIVA'),
    (2, 2, CURRENT_TIMESTAMP, 'ATIVA'),
    (3, 3, CURRENT_TIMESTAMP, 'FINALIZADA'),
    (4, 4, CURRENT_TIMESTAMP, 'FINALIZADA'),
    (5, 5, CURRENT_TIMESTAMP, 'ATIVA'),
    (6, 6, CURRENT_TIMESTAMP, 'ATIVA'),
    (7, 7, CURRENT_TIMESTAMP, 'ATIVA'),
    (8, 8, CURRENT_TIMESTAMP, 'ATIVA'),
    (9, 9, CURRENT_TIMESTAMP, 'FINALIZADA');
   
-- Populando a tabela 'foto_ocorrencia'
INSERT INTO foto_ocorrencia (cod_ocorrencia, data_criacao, dsc_path_bucket, dsc_hash)
VALUES 
    (1, CURRENT_TIMESTAMP, 's3://bucket/foto1.jpg', 'hashfoto1'),
    (2, CURRENT_TIMESTAMP, 's3://bucket/foto2.jpg', 'hashfoto2'),
    (3, CURRENT_TIMESTAMP, 's3://bucket/foto3.jpg', 'hashfoto3'),
    (4, CURRENT_TIMESTAMP, 's3://bucket/foto4.jpg', 'hashfoto4'),
    (5, CURRENT_TIMESTAMP, 's3://bucket/foto5.jpg', 'hashfoto5'),
    (6, CURRENT_TIMESTAMP, 's3://bucket/foto6.jpg', 'hashfoto6'),
    (7, CURRENT_TIMESTAMP, 's3://bucket/foto7.jpg', 'hashfoto7'),
    (8, CURRENT_TIMESTAMP, 's3://bucket/foto8.jpg', 'hashfoto8'),
    (9, CURRENT_TIMESTAMP, 's3://bucket/foto9.jpg', 'hashfoto9');