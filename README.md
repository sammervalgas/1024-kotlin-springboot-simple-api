# API de Veículos - Kotlin e Springboot

## Descrição

Este projeto foi desenvolvido utilizando **Kotlin** e **Spring Boot**.

Ele é uma API RESTful para gerenciar veículos, permitindo realizar operações CRUD (criação, leitura, atualização e exclusão) em uma base de dados de veículos. O banco de dados utilizado é o **H2**, um banco de dados em memória, ideal para desenvolvimento e testes.



## Pré-requisitos

Antes de rodar o projeto, certifique-se de ter as seguintes ferramentas instaladas:

- **JDK 17** ou superior
- **Gradle** (opcional, pois o wrapper está configurado no projeto)



## Configurações

### Banco de Dados H2

O projeto utiliza o banco de dados em memória **H2**, 
que não necessita de configurações externas adicionais. 
As tabelas e dados serão criados dinamicamente na memória enquanto a aplicação estiver rodando.

As configurações básicas do H2 já estão incluídas no arquivo `application.properties`:

```text
spring.datasource.url=jdbc:h2:mem:veiculos_db
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
```

Acesse o console do H2 em:

```text
http://localhost:8080/h2-console
```

Utilize as credenciais:

```text
JDBC URL: jdbc:h2:mem:veiculos_db
User Name: sa
Password:
```

---

## Executando a Aplicação

1. Clone o repositório:
```bash
git clone https://github.com/sammervalgas/1024-kotlin-springboot-simple-api.git
cd 1024-kotlin-springboot-simple-api
```

2. Execute o projeto com o **Gradle Wrapper**:
```bash
./gradlew bootRun
```

## Executando Testes

Para executar os testes do projeto, utilize o seguinte comando:

```bash
./gradlew test
```

## Acessando a API

Após a aplicação estar rodando, você pode acessar a API na URL:

```text
http://localhost:8080/veiculos
```
### Verbos REST e Exemplo de cURL

#### 1. **GET** - Buscar todos os veículos
Retorna uma lista com todos os veículos cadastrados.

**cURL**:
```bash
curl -X GET http://localhost:8080/veiculos
```

#### 2. **GET** - Buscar veículo por ID
Retorna os dados de um veículo específico pelo seu ID.

**cURL**:
```bash
curl -X GET http://localhost:8080/veiculos/1
```

#### 3. **POST** - Criar um novo veículo
Adiciona um novo veículo na base de dados.

**Exemplo de corpo da requisição**:
```json
{
    "marca": "BMW",
    "modelo": "X1",
    "ano": 2021,
    "isClassico": false
}
```

**cURL**:
```bash
curl -X POST http://localhost:8080/veiculos -H "Content-Type: application/json" -d '{"marca": "BMW", "modelo": "X1", "ano": 2021, "isClassico": false}'
```

#### 4. **PUT** - Atualizar um veículo existente
Atualiza os dados de um veículo específico pelo ID.

**Exemplo de corpo da requisição**:
```json
{
    "marca": "Toyota",
    "modelo": "Corolla",
    "ano": 2020,
    "isClassico": false
}
```

**cURL**:
```bash
curl -X PUT http://localhost:8080/veiculos/1 -H "Content-Type: application/json" -d '{"marca": "Toyota", "modelo": "Corolla", "ano": 2020, "isClassico": false}'
```

#### 5. **DELETE** - Remover um veículo
Remove um veículo específico da base de dados pelo ID.

**cURL**:
```
curl -X DELETE http://localhost:8080/veiculos/1
```

## Notas Importantes

- O projeto inclui um **DataLoader** que preenche o banco de dados com alguns veículos de exemplo quando ele está vazio.
- A aplicação pode ser configurada para trabalhar com outros bancos de dados além do H2, bastando alterar as 
dependências e propriedades correspondentes.

  
## Conclusão

A API de Veículos foi projetada para ser simples de rodar e 
estender, utilizando uma estrutura moderna com diversos exemplos e
Kotlin e Spring Boot.

## Autores
- Sammer Valgas | XGH Expert