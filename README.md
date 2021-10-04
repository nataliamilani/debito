#Projeto final referente a matéria de Microservices & Serverless Architecture
##Curso: MBA em Full Stack Developer

**Aplicação:** Débito

**Visão geral:** Essa aplicação tem por objetivo registrar movimentações de débito.
Sendo assim, nela temos os request para os seguintes objetivos:
- Criar uma transação de débito;
- Consultar todas as transações de débito para especifico um tipo de conta (conta corrente ou investimento);
- Consultar todas transações de débito para todas as contas em geral;
- Consultar todas transações de débito (extrato) para uma conta corrente específica;
- Consultar todas transações de débito (extrato) para uma conta investimento específica;
- Consultar saldo sumarizado de uma conta corrente específica;
- Consultar saldo sumarizado de uma conta investimento específica;

**Requisitos minimos de instalação:**
- Java 11
- Docker desktop
- Maven

**Tecnologias utilizadas:**
- Spring Boot
- Java 11
- Maven
- Junit

**Monitoração:**
- Prometheus

**Segurança:**
- API Gateway

**Disponibilidade:**
- Eureka

**Postman - link para import na plataforma:**
- https://www.getpostman.com/collections/f8eea8546a1af821ef31

**OBS:** Para a execução das aplicações locais, aplicação de débito, **DEPENDE** da aplicação eureka (https://github.com/nataliamilani/eureka) já estar online para receber os registros, e também das aplicação API-Gateway (https://github.com/nataliamilani/api-gateway) e Prometheus (https://github.com/nataliamilani/prometheus).
Para subir os containers, basta utilizar o comando "docker-compose up", no diretório raiz das aplicações, após clonar os repositórios na máquina local.