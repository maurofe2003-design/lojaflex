# 🌸 LojaFlex — E-commerce de Perfumes

Loja virtual de perfumes desenvolvida como projeto de estudo, com back-end em **Java (Spring Boot)** e front-end em **HTML/CSS/JavaScript**. O projeto simula uma plataforma completa de e-commerce: catálogo de produtos, cadastro/login de usuários com autenticação via **JWT**, carrinho, pedidos e pagamento.

🔗 **Demo online:** [lojaflex-zeta.vercel.app](https://lojaflex-zeta.vercel.app/)

---

## 📖 Sobre o projeto

O LojaFlex é uma aplicação full stack que reproduz o fluxo real de uma loja online:

- Um visitante navega pelo catálogo de perfumes, filtra por categoria, marca ou faixa de preço;
- Cria uma conta e faz login (autenticação stateless com JWT);
- Monta um pedido com os itens escolhidos;
- Finaliza a compra informando forma de pagamento (cartão, PIX ou boleto);
- Um administrador tem permissões extras para cadastrar, editar e remover produtos do catálogo.

O projeto foi criado **para fins de aprendizado e prática de desenvolvimento web**, servindo como exercício de arquitetura back-end com Spring Boot, segurança com JWT e integração com front-end estático.

---

## 🚀 Tecnologias utilizadas

**Back-end**
- Java 17
- Spring Boot 4 (Web, Security, Data JPA, Validation)
- JWT (`io.jsonwebtoken` / JJWT) para autenticação stateless
- Spring Security com BCrypt para hash de senhas
- H2 Database (banco em memória, usado em desenvolvimento)
- Maven (gerenciador de dependências e build)

**Front-end**
- HTML5, CSS3 e JavaScript puro, consumindo a API REST do back-end

**Infraestrutura**
- Docker (build multi-stage com Maven + Eclipse Temurin JRE)
- Deploy do front-end na Vercel

---

## 🏗️ Arquitetura do projeto

O back-end segue uma organização em camadas (padrão usado em aplicações Spring Boot):

```
src/main/java/com/parfum/
├── controller/     → Endpoints REST (Auth, Perfume, Order)
├── service/        → Regras de negócio
├── repository/     → Acesso a dados (Spring Data JPA)
├── model/          → Entidades JPA (User, Perfume, Order, OrderItem)
├── dto/            → Objetos de transferência de dados (requests/responses)
├── security/       → Filtro e utilitário JWT
└── config/         → Configuração de segurança e carga inicial de dados
```

O front-end estático (`index.html`) fica na raiz do projeto e também é servido pelo próprio Spring Boot a partir de `src/main/resources/static`.

---

## 🔑 Funcionalidades

### Autenticação
- Cadastro de novos usuários (`/api/auth/register`)
- Login com geração de token JWT (`/api/auth/login`)
- Senhas armazenadas com hash BCrypt
- Perfis de acesso: `USER` e `ADMIN`

### Catálogo de perfumes
- Listar todos os produtos
- Buscar produto por ID
- Pesquisar por termo (`nome`, palavra-chave)
- Filtrar por categoria (`masculino`, `feminino`, `unissex`)
- Filtrar por faixa de preço (mínimo/máximo)
- Criar, atualizar e remover produtos — **restrito a administradores**

### Pedidos e pagamento
- Criar pedido com múltiplos itens (autenticado)
- Consultar pedidos do próprio usuário
- Processar pagamento (cartão de crédito/débito, PIX ou boleto)

### Dados iniciais (seed)
Ao subir a aplicação pela primeira vez, o sistema já cria automaticamente:
- Um usuário administrador padrão
- 8 perfumes de exemplo (marcas como Chanel, Dior, Tom Ford, YSL, Carolina Herrera)

---

## 📡 Endpoints da API

| Método | Endpoint                          | Descrição                              | Acesso        |
|--------|------------------------------------|-----------------------------------------|---------------|
| POST   | `/api/auth/register`               | Cadastra um novo usuário                | Público       |
| POST   | `/api/auth/login`                  | Autentica e retorna token JWT           | Público       |
| GET    | `/api/perfumes`                    | Lista todos os perfumes                 | Público       |
| GET    | `/api/perfumes/{id}`               | Detalha um perfume específico           | Público       |
| GET    | `/api/perfumes/search?q=`          | Busca perfumes por termo                | Público       |
| GET    | `/api/perfumes/category/{categoria}` | Filtra por categoria                  | Público       |
| GET    | `/api/perfumes/preco?min=&max=`    | Filtra por faixa de preço               | Público       |
| POST   | `/api/perfumes`                    | Cadastra um novo perfume                | Admin         |
| PUT    | `/api/perfumes/{id}`               | Atualiza um perfume                     | Admin         |
| DELETE | `/api/perfumes/{id}`               | Remove um perfume                       | Admin         |
| POST   | `/api/orders`                      | Cria um novo pedido                     | Autenticado   |
| GET    | `/api/orders`                      | Lista os pedidos do usuário logado      | Autenticado   |
| POST   | `/api/payment`                     | Processa o pagamento de um pedido       | Autenticado   |

---

## ⚙️ Como rodar o projeto localmente

### Pré-requisitos
- [Java 17+](https://adoptium.net/)
- [Maven](https://maven.apache.org/) (ou use o `mvnw` incluso no projeto)
- Docker (opcional, para rodar em container)

### 1. Clonar o repositório
```bash
git clone https://github.com/maurofe2003-design/lojaflex.git
cd lojaflex
```

### 2. Rodar com Maven
```bash
./mvnw spring-boot:run
```
No Windows, use `mvnw.cmd spring-boot:run`.

A aplicação sobe em `http://localhost:8080`, já com o banco H2 em memória e os dados iniciais carregados.

### 3. Rodar com Docker
```bash
docker build -t lojaflex .
docker run -p 8080:8080 lojaflex
```

### 4. Credenciais do administrador padrão
```
E-mail: admin@flex.com
Senha:  admin123
```

### 5. Testando a API
Após subir a aplicação, você pode testar os endpoints com `curl`, Postman ou Insomnia. Exemplo de login:
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"admin@flex.com","password":"admin123"}'
```
O retorno inclui um `token` JWT que deve ser enviado no header `Authorization: Bearer <token>` nas rotas protegidas.

---

## 🗄️ Banco de dados

Por padrão, o projeto usa **H2** (banco em memória), ideal para desenvolvimento e testes — os dados são reiniciados a cada execução. O `pom.xml` já contém, comentadas, as dependências para migrar para **MySQL** ou **PostgreSQL** em um ambiente de produção; basta descomentar a dependência desejada e configurar a conexão em `application.properties`.

---

## 📌 Roadmap / possíveis melhorias

- [ ] Migrar de H2 para um banco persistente (PostgreSQL/MySQL) em produção
- [ ] Adicionar testes automatizados (unitários e de integração)
- [ ] Documentar a API com Swagger/OpenAPI
- [ ] Adicionar paginação nas listagens de produtos
- [ ] Integrar gateway de pagamento real (ex: Stripe, Mercado Pago)
- [ ] Melhorar responsividade do front-end

---




## 📄 Licença

Projeto desenvolvido para fins de aprendizado. Sinta-se à vontade para estudar, clonar e adaptar o código.
