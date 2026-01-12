# Spring Boot Scaffold

A modern Spring Boot scaffold project following Clean Architecture/Hexagonal Architecture principles for rapid application development.

## Tech Stack

- **Java**: 25 LTS (Long-Term Support until 2030)
- **Spring Boot**: 4.0.1
- **MyBatis**: 4.0.1 (Spring Boot Starter)
- **MySQL Connector**: 9.5.0
- **H2 Database**: (for local/test profiles)
- **Lombok**: 1.18.42
- **Maven**: 3.9.12 (via Maven Wrapper)
- **Log4j2**: (replaces default Logback)

## Prerequisites

- JDK 25 (LTS)
- Maven 3.9+ (optional, project includes Maven Wrapper)

## Quick Start

### Clone and Build

```shell
git clone git@github.com:tsfans/spring-boot-scaffold.git
cd spring-boot-scaffold

# Build the project
./mvnw clean install

# Run the application (uses H2 database by default with 'local' profile)
cd spring-boot-scaffold-starter
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

### Generate New Project from Archetype

```shell
# Generate maven archetype and install it into local maven repository
./tool.sh -ga

cd ${your-workspace}
# Generate project according to the archetype
mvn archetype:generate \
  -DarchetypeGroupId=com.github.tsfans \
  -DarchetypeArtifactId=spring-boot-scaffold-archetype \
  -DarchetypeVersion=0.0.1-SNAPSHOT
```

Follow the prompts to define your project properties:
```
Define value for property 'groupId': your-group-id
Define value for property 'artifactId': your-artifact-id
Define value for property 'version' 1.0-SNAPSHOT: your-version
Define value for property 'package' your-group-id: your-package
Y: : Y
```

## Project Structure

This multi-module project follows Clean Architecture principles:

```
spring-boot-scaffold/
├── spring-boot-scaffold-exporter/          # API Layer
│   ├── spring-boot-scaffold-api/           # Service contracts (interfaces, DTOs)
│   └── spring-boot-scaffold-api-adapter/   # HTTP controllers, REST endpoints
├── spring-boot-scaffold-application/       # Use Cases (application services)
├── spring-boot-scaffold-domain/            # Core Business Logic (entities, domain services)
├── spring-boot-scaffold-infrastructure/    # External Adapters (database, external services)
├── spring-boot-scaffold-starter/           # Application Entry Point
└── spring-boot-scaffold-common/            # Shared Utilities (responses, exceptions)
```

### Module Descriptions

- **exporter/api**: Service entry point interfaces and data transfer objects
- **exporter/api-adapter**: API implementations (HTTP REST, RPC, etc.)
- **application**: Use case implementations orchestrating domain services
- **domain**: Core business logic, entities, and domain services (framework-agnostic)
- **infrastructure**: Database repositories (MyBatis), external service adapters
- **starter**: Spring Boot application starter with main class
- **common**: Shared utilities, base responses, and custom exceptions

## Available Profiles

Configure via `application.yml` or command line:

- **local** (default): H2 in-memory database for development
- **dev**: MySQL database on localhost:3306
- **docker**: MySQL in Docker environment

Example:
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

## Build Commands

The project uses Maven Wrapper (`./mvnw`), so Maven installation is optional.

```bash
# Clean and build
./mvnw clean install

# Run tests only
./mvnw test

# Run application
cd spring-boot-scaffold-starter
./mvnw spring-boot:run

# Skip tests
./mvnw clean install -DskipTests

# Build Docker image
./tool.sh -pi  # or: ./tool.sh package-image

# Run with Docker Compose
./tool.sh -dcu  # or: ./tool.sh compose-up
```

See [CLAUDE.md](./CLAUDE.md) for more detailed build and deployment instructions.

## Architecture Highlights

### Clean Architecture Benefits

- **Framework Independence**: Core business logic has no framework dependencies
- **Testability**: Domain logic can be tested independently
- **Flexibility**: Easy to swap infrastructure components (databases, external services)
- **Maintainability**: Clear separation of concerns

### Dependency Rule

Dependencies flow inward:
```
Infrastructure → Domain ← Application ← API Adapter
                  ↑
              (Core - no dependencies)
```

### Data Flow Example (User Creation)

1. HTTP Request → `UserController` (api-adapter)
2. → `UserApplication.create()` (application)
3. → `UserService.create()` (domain - validates business rules)
4. → `UserRepository.save()` (domain interface)
5. → `UserMybatisRepository` (infrastructure implementation)
6. → Database via MyBatis

## Development Notes

### Object Mapping

- **API Layer**: `UserDTO` ↔ Domain `User`
- **Infrastructure**: Domain `User` ↔ `UserPO` (persistence object)

### Optimistic Locking

All domain entities extend `BaseEntity` with a `version` field for optimistic locking. Always include the version when updating entities.

### Code Generation

MyBatis Generator is configured for database entity generation:
```bash
cd spring-boot-scaffold-infrastructure
./mvnw mybatis-generator:generate
```

## Docker Deployment

Build and run with Docker:

```bash
# Build Docker image
./tool.sh -pi

# Start services with docker-compose
./tool.sh -dcu

# Stop services
./tool.sh -dcd
```

The application runs on port 8080 and requires environment variables for external services (MySQL, Redis, etc.). See `Dockerfile` and `docker-compose.yml` for details.

## Contributing

This is a scaffold/template project. Feel free to fork and customize for your needs.

## License

[Your License Here]

## Version History

- **v0.0.1-SNAPSHOT** (January 2026): Spring Boot 4.0.1, JDK 25 LTS, Maven Wrapper 3.9.12