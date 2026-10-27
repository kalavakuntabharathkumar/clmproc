# Healthcare Claims Processing & Payment Reconciliation Platform

**Tech stack:** Java 17, Spring Boot, PostgreSQL, REST APIs, Docker, AWS (EC2, S3, RDS), Git, JUnit, Jira

A portfolio starter for claims intake, status tracking, and payment reconciliation. This is a demo scaffold, not a production healthcare system; use synthetic data only.

## Run locally
Requirements: Java 17+, Maven 3.9+, Docker.

1. Start PostgreSQL: `docker compose up -d postgres`
2. Start the API: `mvn spring-boot:run`
3. Health check: `curl http://localhost:8080/api/health`
4. List claims: `curl http://localhost:8080/api/claims`

## Endpoints
- `GET /api/health`
- `GET /api/claims` (optional `?status=APPROVED`)
- `GET /api/claims/{id}`
- `POST /api/claims`
- `PATCH /api/claims/{id}/status`
- `POST /api/reconciliations`

The resume metrics in the project brief are user-provided and are not measured by this starter scaffold. Only claim results you actually achieved and can explain in interviews.
