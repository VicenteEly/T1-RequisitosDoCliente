# T1 — Requisitos do Cliente: Sistema de Colaboradores

API REST para gestão de colaboradores, com cálculo automático de **comissão** e de **produção**.

Construída com **Java 21**, **Spring Boot 4.1.1**, **Gradle 9.7.1**, **JPA/Hibernate**, **H2** e **Swagger**.

---

## 1. Como rodar

```bash
./gradlew bootRun        # sobe a API em http://localhost:8080
./gradlew test           # roda os testes
```

| Recurso | URL |
|---|---|
| Swagger UI | <http://localhost:8080/swagger-ui.html> |
| OpenAPI (JSON) | <http://localhost:8080/v3/api-docs> |
| Console H2 | <http://localhost:8080/h2-console> |

**Console H2:** JDBC URL `jdbc:h2:mem:colaboradores`, usuário `sa`, senha vazia.

> O banco é **em memória** e recriado a cada reinício (`ddl-auto=create-drop`).

---

## 2. Estrutura do projeto

```
src/main/java/colaboradores/
├── Application.java                 # classe principal
├── entity/                          # domínio (JPA)
│   ├── Colaboradores.java           # PK = matricula
│   ├── Comissao.java                # PK = id gerado
│   ├── Producao.java                # PK = id gerado
│   └── TipoColaboradorEnum.java     # PADRAO | COMISSIONADO | PRODUCAO
├── repository/                      # acesso a dados (Spring Data JPA)
│   ├── ColaboradoresRepository.java
│   ├── ComissaoRepository.java
│   └── ProducaoRepository.java
├── service/                         # regras de negócio
│   ├── ColaboradoresService.java
│   ├── ComissaoService.java
│   └── ProducaoService.java
├── controller/                      # endpoints REST
│   ├── ColaboradoresController.java
│   ├── ComissaoController.java
│   └── ProducaoController.java
├── dto/                             # payloads de entrada
│   ├── ComissaoRequest.java
│   └── ProducaoRequest.java
└── exception/                       # erros padronizados (404 / 400)
    ├── NotFoundException.java
    ├── ErroResposta.java
    └── ApiExceptionHandler.java
```

---

## 3. Modelo de dados

| Tabela | Chave primária | Relacionamento |
|---|---|---|
| `colaboradores` | `matricula` (texto) | — |
| `comissao` | `id` (autoincremento) | `idMatricula` → `colaboradores.matricula` |
| `producao` | `id` (autoincremento) | `idMatricula` → `colaboradores.matricula` |

`Comissao` e `Producao` apontam para o colaborador com `@ManyToOne`.

---

## 4. Regras de negócio

| # | Regra |
|---|---|
| 1 | **Comissão** = `valorVendas × percentual ÷ 100` (calculada pelo serviço, não vem no payload) |
| 2 | **Total da produção** = `quantidadeProduzida × valorUnidade` (idem) |
| 3 | Comissão só pode ser registrada para colaborador **`COMISSIONADO`** |
| 4 | Produção só pode ser registrada para colaborador **`PRODUCAO`** |
| 5 | Percentual de comissão aceita de 0 a 100 |
| 6 | Ao excluir um colaborador, as comissões e produções vinculadas são removidas junto (evita erro de chave estrangeira) |
| 7 | Validações de dados obrigatórios e valores negativos retornam **HTTP 400** |
| 8 | Registro inexistente retorna **HTTP 404** |

> **Premissa assumida:** o `TipoColaboradorEnum` define o tipo de remuneração —
> `PADRAO` = só salário fixo, `COMISSIONADO` = salário + comissão, `PRODUCAO` = salário + produção.

---

## 5. Endpoints

### Colaboradores — `/api/colaboradores`

| Método | Rota | Descrição |
|---|---|---|
| GET | `/api/colaboradores` | lista todos |
| GET | `/api/colaboradores/{matricula}` | busca por matrícula |
| POST | `/api/colaboradores` | cria |
| PUT | `/api/colaboradores/{matricula}` | atualiza |
| DELETE | `/api/colaboradores/{matricula}` | exclui (com dependentes) |
| GET | `/api/colaboradores/{matricula}/comissoes` | comissões do colaborador |
| GET | `/api/colaboradores/{matricula}/producoes` | produções do colaborador |

### Comissões — `/api/comissoes`

| Método | Rota | Descrição |
|---|---|---|
| GET | `/api/comissoes` | lista todas |
| GET | `/api/comissoes/{id}` | busca por id |
| POST | `/api/comissoes/{matricula}` | cria para o colaborador |
| PUT | `/api/comissoes/{id}` | atualiza |
| DELETE | `/api/comissoes/{id}` | exclui |

### Produções — `/api/producoes`

| Método | Rota | Descrição |
|---|---|---|
| GET | `/api/producoes` | lista todas |
| GET | `/api/producoes/{id}` | busca por id |
| POST | `/api/producoes/{matricula}` | cria para o colaborador |
| PUT | `/api/producoes/{id}` | atualiza |
| DELETE | `/api/producoes/{id}` | exclui |

---

## 6. Exemplos de uso

**Criar um colaborador comissionado**

```bash
curl -X POST http://localhost:8080/api/colaboradores \
  -H "Content-Type: application/json" \
  -d '{"matricula":"001","nome":"Ana Souza","salario":1500.00,"tipoColaborador":"COMISSIONADO"}'
```

**Registrar uma comissão** (→ `comissao = 10000 × 7,5 / 100 = 750,00`)

```bash
curl -X POST http://localhost:8080/api/comissoes/001 \
  -H "Content-Type: application/json" \
  -d '{"valorVendas":10000.00,"percentual":7.50}'
```

**Registrar uma produção** (→ `total = 120 × 25,50 = 3060,00`)

```bash
curl -X POST http://localhost:8080/api/producoes/003 \
  -H "Content-Type: application/json" \
  -d '{"quantidadeProduzida":120,"valorUnidade":25.50}'
```

---

## 7. Decisões de design

| Tema | Decisão |
|---|---|
| Identificador | `Colaboradores` usa a **matrícula como PK**; `Comissao` e `Producao` ganham **`Long id` autoincremento** |
| Banco | **H2 em memória** — roda sem instalar nada |
| Persistência | Spring Data JPA + Hibernate, `ddl-auto=create-drop` |
| Erros | `@RestControllerAdvice` devolve JSON padronizado com `timestamp`, `status`, `erro`, `mensagem` |
| Validação | Manual nos services (sem dependência extra de `hibernate-validator`) |
| Documentação | springdoc-openapi (Swagger UI) |
