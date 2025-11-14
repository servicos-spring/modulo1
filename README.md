# Módulo 1
Crie uma API REST utilizando Spring Boot (versão 3+).
A API deve conter um CRUD de Pessoa (Criar, Ler, Atualizar e Deletar), com os seguintes requisitos:

O retorno do serviço deve ser paginado, mostrando 10 itens por página.

Apenas pessoas com o atributo ativo = true devem ser retornadas.

Utilize o banco de dados da sua escolha e crie uma tabela com o seguinte padrão:

ID NOME DT_NASCIMENTO ATIVO
Os logs da aplicação devem ser enviados ao Graylog.

No seu docker-compose, adicione todas as imagens utilizadas (banco de dados, Graylog, aplicação, etc.).

# Arquitetura
## domain/

Contém toda a regra de negócio pura, independente de framework.

entity/ – Entidades de domínio

exception/ – Exceções específicas da regra de negócio.

repository/ – Interfaces que definem contratos de persistência.

service/ – Serviços de domínio que implementam as regras de negócio.

## springframework/

Camada de infraestrutura responsável por integrar o domínio com tecnologias externas (Spring Boot, banco, web, etc.).

configuration.service/ – Configurações e injeções de dependências dos serviços do domínio.

controller/ – Endpoints REST.

adapter/ – Adapta chamadas HTTP para o serviço de domínio.

dto/ – Objetos de request e response das requisições

advice/ – Tratamento global de erros.

repository/

adapter/ – Adapta o PersonRepository do domínio para a implementação concreta.

client/ – Cliente específico (ex.: comunicação com Mongo).

orm/ – Modelo de persistência (ex.: PersonOrm) e implementação (PersonRepositoryImpl).
