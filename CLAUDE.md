# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build and Run Commands

### Maven Wrapper
This project uses Maven Wrapper. All Maven commands should use `./mvnw` (or `mvnw.cmd` on Windows).

### Common Commands

**Build and test:**
```bash
./mvnw clean install
```

**Run tests only:**
```bash
./mvnw test
```

**Run a single test:**
```bash
./mvnw test -Dtest=ClassName#methodName
```

**Run application (via Spring Boot plugin):**
```bash
cd spring-boot-scaffold-starter && mvn spring-boot:run
```
Or use the tool script:
```bash
./tool.sh -br
# or: ./tool.sh boot-run
```

**Build and run packaged JAR:**
```bash
./tool.sh -jr
# or: ./tool.sh jar-run
# Equivalent to: mvn clean install && java -jar spring-boot-scaffold-starter/target/spring-boot-scaffold-starter.jar
```

**Deploy API module to remote repository:**
```bash
./tool.sh -d
# or: ./tool.sh deploy
```

**Generate Maven archetype from project:**
```bash
./tool.sh -ga
# or: ./tool.sh generate-archetype
```

**Docker commands:**
```bash
# Build Docker image
./tool.sh -pi  # package-image

# Run container
./tool.sh -rc  # run-container

# Start with docker-compose
./tool.sh -dcu  # compose-up

# Stop docker-compose
./tool.sh -dcd  # compose-down
```

### Spring Profiles

The application supports multiple profiles (configured in `spring-boot-scaffold-starter/src/main/resources/`):

- **dev** (default): MySQL database on localhost:3306
- **local**: H2 in-memory database for local development
- **docker**: MySQL in Docker environment

Switch profiles:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

Or set in `application.yml`:
```yaml
spring:
  profiles:
    active: local
```

## Project Architecture

This is a **multi-module Spring Boot application** following **Clean Architecture/Hexagonal Architecture** principles with clear separation between layers.

### Module Structure and Dependencies

```
┌─────────────────────────────────────────────────┐
│  spring-boot-scaffold-starter (Entry Point)    │
│  - Executable JAR, Spring Boot application      │
└────────────────┬────────────────────────────────┘
                 │
                 ├─► api-adapter ──┐
                 │                 │
                 └─► infrastructure─┤
                                    │
                 ┌──────────────────┘
                 │
┌────────────────▼────────────────────────────────┐
│  spring-boot-scaffold-exporter (API Layer)     │
│  ├─ api: Service contracts (interfaces, DTOs)  │
│  └─ api-adapter: HTTP controllers              │
└────────────────┬────────────────────────────────┘
                 │
                 ▼
┌────────────────────────────────────────────────┐
│  spring-boot-scaffold-application              │
│  - Use cases, orchestrates domain services     │
└────────────────┬────────────────────────────────┘
                 │
                 ▼
┌────────────────────────────────────────────────┐
│  spring-boot-scaffold-domain (Core)           │
│  - Business logic, entities, domain services   │
│  - Repository interfaces (not implementations) │
│  - Framework-agnostic                          │
└────────────────┬────────────────────────────────┘
                 │
                 ▼
┌────────────────────────────────────────────────┐
│  spring-boot-scaffold-infrastructure           │
│  - Repository implementations (MyBatis)        │
│  - External service adapters                   │
│  - Database entities (PO objects)              │
└─────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────┐
│  spring-boot-scaffold-common (Utilities)       │
│  - BaseResponse, ResponseCode, exceptions      │
│  - Cross-cutting concerns                      │
└─────────────────────────────────────────────────┘
```

### Key Architectural Concepts

**Dependency Rule:** Dependencies flow downward. Domain layer has minimal dependencies (only Spring Context). Infrastructure implements domain interfaces.

**Data Flow (Example: User Creation):**
1. HTTP Request → `UserController` (api-adapter)
2. → `UserApplication.create()` (application layer)
3. → `UserService.create()` (domain service - validates business rules)
4. → `IdentifierService.generateIdentifier()` (infrastructure)
5. → `UserRepository.save()` (domain interface)
6. → `UserMybatisRepository` (infrastructure implementation)
7. → MyBatis → Database

**Object Conversions:**
- **API Layer:** `UserDTO` ↔ Domain `User`
- **Infrastructure:** Domain `User` ↔ `UserPO` (persistence object)

**Repository Pattern:** Domain defines `UserRepository` interface, infrastructure provides `UserMybatisRepository` implementation.

### Important Package Structure

- **Controllers:** `com.github.tsfans.exporter.api.adapter.http`
- **Application Services:** `com.github.tsfans.application`
- **Domain Entities:** `com.github.tsfans.domain.entity`
- **Domain Services:** `com.github.tsfans.domain.service`
- **MyBatis Mappers:** `com.github.tsfans.infrastructure.db.mapper`
- **Persistence Objects:** `com.github.tsfans.infrastructure.db.po`

## Technology Stack

- **Java 21** - Language version (JDK 21 required)
- **Spring Boot 3.3.0** - Framework
- **MyBatis 3.0.3** - ORM for database access
- **MySQL** - Production database (dev/docker profiles)
- **H2** - In-memory database (local/test profiles)
- **Log4j2** - Logging (Logback excluded)
- **Lombok** - Annotation processing for POJOs

## Database and Persistence

### MyBatis Configuration

**Mapper XML location:** `classpath:mapper/mysql/*.xml`

**Mapper interface scanning:** `com.github.tsfans.infrastructure.db.mapper`

**Type aliases package:** `cn.swift.infrastructure.db.po` (configured in `application.yml`)

### Database Schema Initialization

For local/test profiles, H2 database is initialized from:
- Schema: `classpath:sql/users.sql`
- Configured in profile-specific `application.yml`

For Docker, MySQL is initialized via `init.sql` volume mount.

### MyBatis Generator

Code generation configured in `spring-boot-scaffold-infrastructure/src/main/resources/mybatis_generator_config.xml`.

Uses Lombok plugin for generating mappers and POs.

## Module-Specific Notes

### API Module Publishing

The `spring-boot-scaffold-api` module is designed to be published as a contract library for external consumers:
- Publishes source JAR via `maven-source-plugin`
- Contains only interfaces and DTOs
- Minimal dependencies (Spring Validation, common module)

### Version Management

**Optimistic Locking:** All domain entities extend `BaseEntity` which includes a `version` field for optimistic locking.

When updating entities, always include the current version to prevent concurrent modification issues.

### Identifier Generation

The `IdentifierService` (domain interface) is implemented by `DistributedIdentifierService` (infrastructure).

Current implementation uses timestamp-based IDs. For production, consider integrating distributed ID generators (Snowflake, database sequences, etc.).

## Configuration Files

### Thread Pool Settings

Default thread pool configuration in `application.yml`:
- Core pool size: 10
- Max pool size: 20
- Queue capacity: 10

### HTTP Client Pool

Configured for external service calls:
- Max total connections: 200
- Max per route: 100
- Connection timeout: 10s
- Socket timeout: 30s

## Docker Deployment

**Multi-stage build:** Uses Eclipse Temurin JDK 21 JRE image.

**Environment variables expected:**
- `MYSQL_USERNAME`, `MYSQL_PASSWORD`
- `REDIS_PASSWORD` (if Redis integration added)
- `ROCKETMQ_USERNAME`, `ROCKETMQ_PASSWORD` (if MQ integration added)
- `NACOS_USERNAME`, `NACOS_PASSWORD` (if Nacos integration added)

**Port:** Application runs on port 8080 (exposed in Dockerfile).

**Docker Compose:** Includes MySQL 8.0 service with volume persistence and init script.

## Generating Projects from This Scaffold

This project can be used as a Maven archetype template:

1. Generate archetype: `./tool.sh -ga`
2. In target directory, generate new project:
```bash
mvn archetype:generate \
  -DarchetypeGroupId=com.github.tsfans \
  -DarchetypeArtifactId=spring-boot-scaffold-archetype \
  -DarchetypeVersion=0.0.1-SNAPSHOT
```

Follow prompts to specify new project's groupId, artifactId, version, and package.
