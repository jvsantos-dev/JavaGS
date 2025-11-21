# CareerBooster API – Plataforma de Upskilling / Reskilling

## 1. Descrição do Projeto
O **CareerBooster** é uma **API RESTful** desenvolvida em **Java 17** com **Spring Boot**, voltada para a requalificação (reskilling) e o aperfeiçoamento (upskilling) de profissionais para o futuro do trabalho (2030+).

A plataforma permite:
- Cadastro de usuários.
- Listagem e busca de usuários.
- Atualização e exclusão de usuários.
- Preparação para novas profissões e competências tecnológicas/humanas.

O projeto segue **arquitetura em camadas**:
- **Controller** → Recebe requisições HTTP.
- **Service** → Contém regras de negócio e validações.
- **Repository / DAO** → Comunicação com banco de dados relacional.

---

## 2. Tecnologias e Dependências
- **Java 17**
- **Spring Boot 3.x**
- **Spring Web**
- **Spring Data JPA / DAO Pattern**
- **Bean Validation (jakarta.validation)**
- **H2 Database** (ou MySQL/Oracle)
- **Lombok**
- **Spring Security** (opcional)
- **springdoc-openapi** (Swagger)
- **Maven** (gerenciamento de dependências e build)
- **DevTools** (hot-reload durante desenvolvimento)

---

## 3. Estrutura do Projeto

src/main/java
└─ com.gs.CareerBooster
├─ controller
│ └─ UserController.java
├─ service
│ └─ UserService.java
├─ repository
│ └─ UserRepository.java
├─ model
│ └─ UserModel.java
├─ dto
│ ├─ CreateUserDto.java
│ ├─ UpdateUserDto.java
│ └─ UserResponseDto.java
└─ exception
├─ UserServiceException.java
└─ UserNotFoundExcepetion.java


---

## 4. Configuração do Banco de Dados
No **application.properties**:

```properties
spring.datasource.url=jdbc:h2:mem:careerbooster
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=create
spring.h2.console.enabled=true

    Banco em memória H2 (pode substituir por MySQL ou Oracle)

    spring.jpa.hibernate.ddl-auto=create → cria tabelas automaticamente

    H2 Console disponível em /h2-console

5. Como Executar

    Clonar o repositório:

git clone <url-do-repositorio>
cd Aplicativo

    Executar via Maven:

mvn spring-boot:run

A API estará disponível em:

http://localhost:8080

6. Endpoints da API
Criar Usuário

POST /users

Request Body (JSON):

{
  "name": "João Victor",
  "email": "teste@gmail.com",
  "username": "joaovictor",
  "password": "123456",
  "area_interest": "Desenvolvimento"
}

Response (201 Created):

{
  "id": 1,
  "name": "João Victor",
  "email": "teste@gmail.com",
  "username": "joaovictor",
  "area_interest": "Desenvolvimento"
}

Buscar Usuário por ID

GET /users/{id}

Response (200 OK):

{
  "id": 1,
  "name": "João Victor",
  "email": "teste@gmail.com",
  "username": "joaovictor",
  "area_interest": "Desenvolvimento"
}

Listar Todos os Usuários

GET /users

Response (200 OK):

[
  {
    "id": 1,
    "name": "João Victor",
    "email": "teste@gmail.com",
    "username": "joaovictor",
    "area_interest": "Desenvolvimento"
  }
]

Atualizar Usuário

PUT /users/{id}

Request Body (JSON):

{
  "id": 1,
  "name": "João V.",
  "email": "joao@gmail.com",
  "username": "joaov",
  "area_interest": "Dev Fullstack"
}

Response (200 OK):

{
  "id": 1,
  "name": "João V.",
  "email": "joao@gmail.com",
  "username": "joaov",
  "area_interest": "Dev Fullstack"
}

Deletar Usuário

DELETE /users/{id}

Response (204 No Content)
7. Validação e Tratamento de Erros

    Bean Validation para campos obrigatórios e formato de e-mail.

    Exceções customizadas:

        UserNotFoundExcepetion → retorna 404 Not Found

        RuntimeException → conflitos de e-mail/username retornam 400 Bad Request

    Centralizado via @RestControllerAdvice (opcional, recomendado para respostas padronizadas)

8. Segurança

    Senhas devem ser criptografadas (recomendado BCrypt no Service)

    Endpoints podem ser protegidos via Spring Security (opcional, mas recomendado)

9. Documentação da API

    Recomendado adicionar Swagger / OpenAPI com springdoc-openapi

    Acessível via:

http://localhost:8080/swagger-ui.html

10. Testes Rápidos

    Usar Postman, Insomnia ou curl

    Exemplo com curl:

curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{"name":"João Victor","email":"teste@gmail.com","username":"joaovictor","password":"123456","area_interest":"Desenvolvimento"}'

11. Observações

    O projeto utiliza DTOs para garantir segurança e não expor campos sensíveis (ex.: senha).

    Hot-reload disponível com spring-boot-devtools para não reiniciar o Maven manualmente.

    Banco configurado para criação automática via JPA, mas pode ser substituído por MySQL ou Oracle.
