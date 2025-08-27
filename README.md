![dreamjob.png](src/main/resources/static/dreamjob.png)
# DreamJob
This project is an API for managing and retrieving job offers and related data.
It is intended to provide endpoints for job listings, including labor functions, job locations, 
and organization information - all managed via a single controller.

## Technology stack
- **Java 21**
- **Database**: PostgreSQL, Hibernate 6.6.2
- **Spring Boot 3.4.0**: Spring Core, Spring Web, Spring Data, Scheduler
- **Web Development**: Tomcat 10.1.33, JSON, Multithreading
- **HTTP & HTML Processing Libraries**: OkHttp, Jsoup
- **Tools**: Maven 3.9.9, Liquibase 4.29.2, Docker 27.3.1, Swagger OpenAPI

## Launch instructions
After cloning this project, the simplest setup uses Docker Compose. You will need to create a 
`.env` file at the project root to specify Docker ports and PostgreSQL database information. 
The database will be pulled from Docker Hub (no local install needed).

Example `.env` file:
```dotenv
POSTGRES_USER=postgres_user
POSTGRES_PASSWORD=postgres1234
POSTGRES_DB=dreamjob
POSTGRES_LOCAL_PORT=5433
POSTGRES_DOCKER_PORT=5432

SPRING_LOCAL_PORT=8081
SPRING_DOCKER_PORT=8080
DEBUG_PORT=5005
```

To start the application, navigate to the project root and run:
```console
docker-compose up
```
Once started, the default Docker server URI is http://localhost:8081 defined by your `.env` settings.

If you want to run the project locally, you must also have Java 21+ and Maven 3.9.9+ installed. 
Default local server URI: http://localhost:8080

## Functionality (available endpoints)
The project exposes a set of REST endpoints for managing and retrieving job data.
The main controller provides endpoints for:

- Retrieving job listings
- Accessing detailed information for a specific job

**Swagger-UI documentation:**
Visit `/swagger-ui/index.html` on your running server (e.g., http://localhost:8081/swagger-ui/index.html) 
for live interactive documentation and a full overview of available REST endpoints.

### Example endpoints
- `POST /api/jobs` — Fetch jobs from https://jobs.techstars.com/ and save it to local database
- `GET /api/jobs/{id}` — Get detailed info about a specific job from local database
- `POST /api/jobs/local` — Get jobs from local database
