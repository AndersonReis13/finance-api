<div align="center">
# Finance API - Spring Boot & Docker

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Este projeto tem como base uma criação de uma API REST para gerenciamentos de rendas, transações, metas e projeções pessoais.
Focadas em regras de negócio, sendo escalável para futuras features.
    
</div>

---

### 🛠️ Tecnologias

|                     | Funcionalidade                                                       |
|---------------------|----------------------------------------------------------------------|
| **Java 17+**        | Linguagem de compilação                                              |
| **Spring Boot**     | Framework para criação da API                                        |
| **Spring Data JPA** | Persistência de dados e comunicação com o banco                      |
| **Hibernate**       | Implementação do JPA                                                 |
| **PostgreSQL**      | Banco de dados                                                       |
| **Maven**           | Gerenciador de dependências e build                                  |
| **Docker**          | Criação de conteiner com configuração de ferramentas(banco de dados) |
| **Postman**         | Testes e validação dos endpoints                                     |

---

### 🚀 Como Executar

- Java JDK 17+
- Maven 3.6+
- Docker 
- Postman

```bash
  #Clone o repositório
  git clone https://github.com/AndersonReis13/finance-api.git
  
  #Execute o comando docker para baixar as dependencias:
  docker-compose up -d
  
  #Crie um arquivo na pasta /resources, chamado: application-secret.yml
  JWT_SECRET_KEY: (deve conter 32 caracteres)
  JWT_EXPIRATION: 86400000 
  
  #Execute o comando maven para rodar a aplicação:
  mvn spring-boot:run   
```
Porta da API: http://localhost:8080

### 📡 API

🔐 Autenticação
A API utiliza autenticação baseada em token JWT (JSON Web Token). 
Para acessar endpoints protegidos, inclua o token no header das requisições.

```bash
  Authorization: Bearer <seu_token_jwt>
```

### 🔑 Autenticação
- **POST** `/auth/login` → Gera token JWT
- **POST** `/auth/register` → Cria novo usuário

### 👤 Usuários
- **GET** `/users/{id}` → Detalhes do usuário autenticado
- **PUT** `/users` → atualiza o usuário

### 📂 Categorias
- **GET** `/category` → Lista categorias
- **GET** `/category/{id}` → Lista a categoria por id
- **POST** `/category` → Cadastra categoria
- **PUT** `/category` → atualiza o usuario 
- **DELETE** `/category/{id}` → deleta usuario

### 📋 Contas
- **GET** `/account` → Lista as contas
- **POST** `/account` → Cadastra conta
- **PUT** `/account/update` → atualiza a conta
- **DELETE** `/account/{id}` → deleta conta


### 📋 Transações
- **POST** `/transaction` → Criar transação
- **GET** `/transaction` → Listar as tarefas do usuário
- **GET** `/transaction/{id}` → pega uma transação
- **DELETE** `/transaction/{id}` → deleta transa




