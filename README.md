[![CI/CD Pipeline](https://github.com/juansebastian133/Laboratorio-de-arquitectura-de-software-1-2026/actions/workflows/build.yml/badge.svg)](https://github.com/juansebastian133/Laboratorio-de-arquitectura-de-software-1-2026/actions/workflows/build.yml)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=juansebastian133_Laboratorio-de-arquitectura-de-software-1-2026&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=juansebastian133_Laboratorio-de-arquitectura-de-software-1-2026)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=juansebastian133_Laboratorio-de-arquitectura-de-software-1-2026&metric=bugs)](https://sonarcloud.io/summary/new_code?id=juansebastian133_Laboratorio-de-arquitectura-de-software-1-2026)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=juansebastian133_Laboratorio-de-arquitectura-de-software-1-2026&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=juansebastian133_Laboratorio-de-arquitectura-de-software-1-2026)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=juansebastian133_Laboratorio-de-arquitectura-de-software-1-2026&metric=coverage)](https://sonarcloud.io/summary/new_code?id=juansebastian133_Laboratorio-de-arquitectura-de-software-1-2026)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=juansebastian133_Laboratorio-de-arquitectura-de-software-1-2026&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=juansebastian133_Laboratorio-de-arquitectura-de-software-1-2026)
[![Duplicated Lines](https://sonarcloud.io/api/project_badges/measure?project=juansebastian133_Laboratorio-de-arquitectura-de-software-1-2026&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=juansebastian133_Laboratorio-de-arquitectura-de-software-1-2026)
[![Reliability](https://sonarcloud.io/api/project_badges/measure?project=juansebastian133_Laboratorio-de-arquitectura-de-software-1-2026&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=juansebastian133_Laboratorio-de-arquitectura-de-software-1-2026)
[![Security](https://sonarcloud.io/api/project_badges/measure?project=juansebastian133_Laboratorio-de-arquitectura-de-software-1-2026&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=juansebastian133_Laboratorio-de-arquitectura-de-software-1-2026)
[![Maintainability](https://sonarcloud.io/api/project_badges/measure?project=juansebastian133_Laboratorio-de-arquitectura-de-software-1-2026&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=juansebastian133_Laboratorio-de-arquitectura-de-software-1-2026)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=juansebastian133_Laboratorio-de-arquitectura-de-software-1-2026&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=juansebastian133_Laboratorio-de-arquitectura-de-software-1-2026)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=juansebastian133_Laboratorio-de-arquitectura-de-software-1-2026&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=juansebastian133_Laboratorio-de-arquitectura-de-software-1-2026)
[![Known Vulnerabilities](https://snyk.io/test/github/juansebastian133/Laboratorio-de-arquitectura-de-software-1-2026/badge.svg)](https://snyk.io/test/github/juansebastian133/Laboratorio-de-arquitectura-de-software-1-2026)

# Banco UdeA

API REST de un banco sencillo, construida con Spring Boot, que se integra con GitHub Actions, SonarCloud, JaCoCo, Snyk, Docker Hub y Render para tener un entorno de integración y despliegue continuo.

Operaciones disponibles:

* Consultar clientes
* Crear un cliente
* Transferir dinero entre cuentas
* Consultar las transacciones de una cuenta
* Chequeo de estado y versión de la aplicación

La aplicación usa una base de datos H2 en memoria. Al iniciar se cargan dos clientes de ejemplo (cuentas `1001` y `1002`), por lo que no necesita instalar ni configurar ninguna base de datos.

## Tecnologías

* Java 17 y Spring Boot
* Spring Data JPA y H2
* MapStruct y Lombok
* JUnit 5 y JaCoCo
* GitHub Actions, SonarCloud y Snyk
* Docker y Docker Hub
* Render

## Estructura de carpetas

En la carpeta `src/main` está el código principal de la aplicación.

En la carpeta `src/test` están las pruebas unitarias.

En la carpeta `.github/workflows` está el pipeline de CI/CD (`build.yml`).

En la raíz están el `pom.xml` y el `Dockerfile`.

## Requisitos previos

* JDK 17
* Git
* Docker (opcional, para ejecutar la imagen)

## Cómo instalarla y ejecutarla

```shell
$ ./mvnw spring-boot:run
```

La aplicación queda disponible en `http://localhost:8080`.

## Cómo probarla

```shell
$ ./mvnw clean verify
```

## Cómo obtener la cobertura de pruebas

La cobertura se mide con JaCoCo. Al ejecutar las pruebas se genera el reporte en `target/site/jacoco/index.html`.

```shell
$ ./mvnw clean verify
```

El mismo reporte (`target/site/jacoco/jacoco.xml`) se envía a SonarCloud desde el pipeline.

## Endpoints

| Método | Ruta | Descripción |
|---|---|---|
| GET | `/` | Chequeo de estado |
| GET | `/version` | Versión de la aplicación |
| GET | `/api/customers` | Lista de clientes |
| GET | `/api/customers/{id}` | Cliente por id |
| POST | `/api/customers` | Crear cliente |
| POST | `/api/transactions` | Transferir dinero |
| GET | `/api/transactions/{accountNumber}` | Transacciones de una cuenta |
| GET | `/h2-console` | Consola de la base de datos H2 |

Ejemplo para crear un cliente:

```shell
$ curl -X POST http://localhost:8080/api/customers \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Maria","lastName":"Lopez","accountNumber":"2001","balance":500000}'
```

Ejemplo para hacer una transferencia:

```shell
$ curl -X POST http://localhost:8080/api/transactions \
  -H "Content-Type: application/json" \
  -d '{"senderAccountNumber":"1001","receiverAccountNumber":"1002","amount":1000}'
```

## Pipeline de CI/CD

El pipeline está definido en `.github/workflows/build.yml` y tiene cinco trabajos encadenados. Cada uno solo corre si el anterior termina bien:

1. **Pruebas unitarias**: compila y ejecuta las pruebas con JaCoCo (`mvn clean verify`).
2. **Análisis SonarCloud**: analiza la calidad del código y envía la cobertura.
3. **Construir JAR**: empaqueta la aplicación y la guarda como artefacto.
4. **Construir y publicar imagen Docker**: construye la imagen y la sube a Docker Hub. Solo en `main`.
5. **Desplegar en Render**: dispara el despliegue en la nube. Solo en `main`.

El pipeline se ejecuta en cada push a cualquier rama, en los pull requests hacia `main` y de forma manual.

### Secretos y variables

En el repositorio, en Settings, Secrets and variables, Actions:

| Tipo | Nombre | Descripción |
|---|---|---|
| Secreto | `SONAR_TOKEN` | Token generado en SonarCloud |
| Secreto | `DOCKER_USERNAME` | Usuario de Docker Hub |
| Secreto | `DOCKER_PASSWORD` | Contraseña o token de acceso de Docker Hub |
| Secreto | `RENDER_DEPLOY_HOOK_URL` | URL del Deploy Hook del servicio en Render |
| Variable | `SONAR_PROJECT_KEY` | Clave del proyecto en SonarCloud |
| Variable | `SONAR_ORGANIZATION` | Organización en SonarCloud |
| Variable | `SONAR_HOST_URL` | `https://sonarcloud.io` |
| Variable | `APP_NAME` | Nombre del artefacto |
| Variable | `DOCKER_REPOSITORY` | Repositorio de la imagen en Docker Hub |

## Estrategia de ramas (trunk based)

Se trabaja con una rama principal (`main`) y ramas cortas para cada cambio:

```shell
$ git checkout -b mi-rama
$ git add .
$ git commit -m "descripcion del cambio"
$ git push -u origin mi-rama
```

1. Al subir la rama, el pipeline ejecuta pruebas, SonarCloud y construcción del JAR.
2. Se abre un Pull Request hacia `main` y se revisan los resultados de los chequeos.
3. Con los chequeos en verde se hace el merge a `main`.
4. El merge a `main` publica la imagen en Docker Hub y despliega en Render.

## Docker

La imagen se publica en Docker Hub como `juangomez133/bancoudea`.

```shell
$ docker pull juangomez133/bancoudea:latest
$ docker run -p 8080:8080 juangomez133/bancoudea:latest
```

Para construirla localmente:

```shell
$ docker build -t bancoudea .
$ docker run -p 8080:8080 bancoudea
```

## Despliegue en la nube (Render)

1. En Render, crear un Web Service desde una imagen existente (Existing Image).
2. Usar la imagen `docker.io/juangomez133/bancoudea:latest`.
3. En Settings, copiar el Deploy Hook y guardarlo como secreto `RENDER_DEPLOY_HOOK_URL`.
4. Cada merge a `main` vuelve a desplegar la aplicación.

Render asigna el puerto con la variable `PORT`, que la aplicación ya lee. Probar el servicio desplegado:

```shell
$ curl https://<URL_DEL_SERVICIO_EN_RENDER>/
$ curl https://<URL_DEL_SERVICIO_EN_RENDER>/api/customers
```
