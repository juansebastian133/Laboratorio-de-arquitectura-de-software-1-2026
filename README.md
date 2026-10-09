[![CI/CD Pipeline](https://github.com/juansebastian133/Laboratorio-de-arquitectura-de-software-1-2026/actions/workflows/build.yml/badge.svg)](https://github.com/juansebastian133/Laboratorio-de-arquitectura-de-software-1-2026/actions/workflows/build.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=juansebastian133_Laboratorio-de-arquitectura-de-software-1-2026&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=juansebastian133_Laboratorio-de-arquitectura-de-software-1-2026)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=juansebastian133_Laboratorio-de-arquitectura-de-software-1-2026&metric=coverage)](https://sonarcloud.io/summary/new_code?id=juansebastian133_Laboratorio-de-arquitectura-de-software-1-2026)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=juansebastian133_Laboratorio-de-arquitectura-de-software-1-2026&metric=bugs)](https://sonarcloud.io/summary/new_code?id=juansebastian133_Laboratorio-de-arquitectura-de-software-1-2026)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=juansebastian133_Laboratorio-de-arquitectura-de-software-1-2026&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=juansebastian133_Laboratorio-de-arquitectura-de-software-1-2026)
[![Known Vulnerabilities](https://snyk.io/test/github/juansebastian133/Laboratorio-de-arquitectura-de-software-1-2026/badge.svg)](https://snyk.io/test/github/juansebastian133/Laboratorio-de-arquitectura-de-software-1-2026)

Simple banking API with the next operations:

* Get customers
* Create customer
* Transfer money
* Get transactions by account
* Health check and application version

Uses an in-memory H2 database, loaded with two sample customers (accounts 1001 and 1002) on every start.

### Folders Structure

In the folder `src/main` is located the main code of the app

In the folder `src/test` are located the unit tests

### How to run it

```shell
$ mvnw spring-boot:run
```

### How to test it

```shell
$ mvnw clean verify
```

### How to run it with Docker

```shell
$ docker pull <DOCKER_USUARIO>/<DOCKER_REPOSITORIO>:latest
$ docker run -p 8080:8080 <DOCKER_USUARIO>/<DOCKER_REPOSITORIO>:latest
```

### Endpoints

* `GET /`
* `GET /version`
* `GET /api/customers`
* `GET /api/customers/{id}`
* `POST /api/customers`
* `POST /api/transactions`
* `GET /api/transactions/{accountNumber}`
* `/h2-console`
