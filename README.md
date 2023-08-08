# drone-feeder
O Drone-Feeder é uma API REST para um sistema de entregas utilizando Drones.<br>
A API permite gerenciar os drones e as entregas exibindo informações como posição geográfica onde será entregue o pacote(latitude e longitude), data e horário da entrega, seu status (criada, em rota, finalizada e cancelada) e o link do vídeo do momento em que o pacote foi recebido. <br>
Todas essas informações são armazenadas em um banco de dados MySQL.<br>

## :mag: Tecnologias utilizadas
- Construção da API - [Java](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html) e [Quarkus](https://quarkus.io/)<br>
- Banco de dados - [MySQL](https://www.mysql.com/) <br>
-  ORM - [Hibernate ORM with Panache](https://quarkus.io/guides/hibernate-orm-panache) <br>
- Testes - [JUnit 5](https://junit.org/junit5/) e [Testcontainers](https://java.testcontainers.org/) <br>
- Cobertura de testes - [JaCoCo Java Code Coverage Library](https://www.eclemma.org/jacoco/)
- Deploy - []() <br>

 ## 📋 Execute o projeto em sua máquina com docker em dev mode

Clone o repositório:

```
git clone git@github.com:tamireshc/Java.git
```

Crie um container MySQL com Docker iniciando o banco drone_feeder
```
docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=drone_feeder -d  mysql:latest
```

Execute
```
./mvnw compile quarkus:dev
```
*As migrations pré definidas serão executadas automaticamente criando as tabelas do projeto. 

## 🕵 Diagrama UML da API <br>
![Drone Feeder drawio](https://github.com/tamireshc/drone-feeder/assets/65035109/2be56cf8-fc37-4176-ba03-1663e45d4d5b)

## 🧪 Executando os testes

```
./mvnw test

```
### Testes de cobertura:<br>

Após rodar os testes procure os resultados na pasta target/jacoco-report 

Os testes deste projeto contemplaram uma cobertura de 95% da linhas.<br>


<img width="1161" alt="Captura de Tela 2023-08-04 às 18 23 38" src="https://github.com/tamireshc/drone-feeder/assets/65035109/5e8b1811-1c4e-4a65-b88c-0098cf706de2">

## 🔎 Documentação da API





.....
## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.






Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)
