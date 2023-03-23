# API - Clientes e Produtos Favoritos
API para gerenciar clientes e seus produtos favoritos.

## Desenvolvimento

O Spring Boot foi escolhido por suas funcionalidades e estrutura de projeto, que ajudam a tornar o projeto e seus códigos mais enxutos e legíveis. A escolha pelo MySQL foi definida por sua simples configuração inicial e sua integração com o flyway, que auxilia no gerenciamento, versionamento e execução de códigos SQL.

## Instalação e Inicialização

Para testar de forma local, é necessário ter instalado os seguintes softwares: Java 17, Maven e MySQL Server. Não é necessária a criação do schema no MySQL de forma manual, apenas que a instância esteja ativa.

Clone o repositório para a máquina e altere o arquivo `src\main\resources\application.properties` para especificar os dados de conexão ao banco de dados MySQL.

Através do terminal, acesse a pasta raíz do repositório e execute os comandos abaixo:

`mvn clean install`
`mvn spring-boot:run`


## Utilização

### Endpoints

#### Clientes

- `GET /clientes`: lista os clientes cadastrados.
- `POST /clientes`: cadastra um novo cliente, informando os parâmetros nome e email em formato JSON.
- `GET /clientes/{clienteId}`: busca as informações de um cliente específico através do seu ID.
- `PUT /clientes/{clienteId}`: atualizar os dados de um cliente específico através do seu ID.
- `DELETE /clientes/{clienteId}`: deletar um cliente específico através de seu ID.

#### Favoritos

- `GET /clientes/{clienteId}/favoritos`: lista todos os produtos favoritos de um cliente específico através do seu ID.
- `POST /clientes/{clienteId}/favoritos`: adiciona um novo produto à lista de produtos favoritos de um cliente específico informando o ID do produto em formato JSON.
- `GET /clientes/{clienteId}/favoritos/{produtoId}`: busca as informações de um produto adicionado à lista de produtos favoritos de um cliente específico através dos ids de cliente e produto.
- `DELETE /clientes/{clienteId}/favoritos/{produtoId}`: deleta um produto favorito específico de um cliente específico, através dos IDs de cliente e produto.
