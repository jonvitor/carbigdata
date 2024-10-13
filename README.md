# Teste Carbigdata

## Pré-requisitos:

Ter docker e docker-compose instalados.

Ter git instalado.

## Configurações iniciais

Clonar o repositorio para ambiente local.

Com o projeto em seu ambiente, temos que gerar o arquivo `.jar`  do projeto.

Comentar a partir da linha 37 do arquivo `docker-compose`

Subir os containers com o comando `docker-compose up`

Acessar o painel do minio pelo caminho [localhost](http://localhost:9001/login)

Acessar o painel com, usuario minioadmin, e senha minio admin

Acessar [localhost](http://localhost:9001/buckets/add-bucket) e criar um bucket com o nome `carbigdata`

Acessar [ http://localhost:9001/access-keys](http://localhost:9001/access-keys) e clickar em "Create access key" criar novas credenciais

Agora devemos salvar o "Access Key" e "Access Secret" em um bloco de notas (você irá precisar mais tarde), e clicar em create.

Com o key e secret criados, devemos adicionar no arquivo `application.properties`, `MINIO_ACCESS_KEY:aqui vai sua key` e `MINIO_ACCESS_SECRET:aqui vai o secret`

Também adicionar o secret e key no arquivo `docker-compose` (ainda deixar as linhas comentadas).

## Testes de integração

Para executar os teste de integração executar o comando `./mvnw verify` dentro da pasta do projeto

## Subindo os containers

Primeiramente Gerar o arquivo `.jar` da aplicação com o comando `.mvn clean install`

Após geração de arquivo `.jar` rodar o comando `docker-compose down -v`

Descomentar a partir da linha 37 o arquivo `docker-compose`

Subir os containers com 'docker-compose up --build'

## Testar a api

Para testar api importar o arquivo json para o Postman ou outro aplicativo similar

[Insomnia_2024-10-13.json](https://github.com/user-attachments/files/17356799/Insomnia_2024-10-13.json)

Para gerar o token chamar o endpoint de autenticação com id: carbigdata-client e secret: carbigdata123

Utilizar o token gerado para realizar as outras chamadas com `Bearer "token gerado"`
