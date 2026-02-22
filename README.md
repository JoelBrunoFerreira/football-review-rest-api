# ⚽ Football Review REST API

A RESTful API built with Spring Boot for managing football players and their reviews. The API includes JWT-based authentication and authorization.

---

## 🛠️ Technologies Used

- **Java 17**
- **Spring Boot 3**
- **Spring Security 6** — JWT Authentication
- **Spring Data JPA** — Database access
- **PostgreSQL** — Relational database (running in Docker)
- **Docker** — PostgreSQL container
- **Lombok** — Boilerplate reduction
- **JJWT 0.11.5** — JWT token generation and validation
- **Maven** — Dependency management

---

## 🐳 Running PostgreSQL with Docker

Make sure you have Docker installed and running. Then start the PostgreSQL container:

```bash
docker run --name football-review-db \
  -e POSTGRES_DB=footballreview \
  -e POSTGRES_USER=your_user \
  -e POSTGRES_PASSWORD=your_password \
  -p 5432:5432 \
  -d postgres
```

Update your `application.properties` accordingly:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/footballreview
spring.datasource.username=your_user
spring.datasource.password=your_password
```

---

## 🚀 Running the Application

```bash
./mvnw spring-boot:run
```

The API will be available at: `http://localhost:8080`

---

## 🔐 Authentication

This API uses **JWT (JSON Web Token)** for authentication.

### Register a new user
```
POST /api/auth/register
```
```json
{
  "userName": "johndoe",
  "password": "secret123"
}
```

### Login and get JWT token
```
POST /api/auth/login
```
```json
{
  "userName": "johndoe",
  "password": "secret123"
}
```

The response will return a JWT token. Include it in all subsequent requests:

```
Authorization: Bearer <your_token>
```

---

## 📋 Endpoints

### Players

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/api/players` | Get all players (paginated) | ✅ |
| GET | `/api/players/{id}` | Get player by ID | ✅ |
| POST | `/api/players` | Create a new player | ✅ |
| PUT | `/api/players/{id}` | Update a player | ✅ |
| PATCH | `/api/players/{id}` | Partially update a player | ✅ |
| DELETE | `/api/players/{id}` | Delete a player | ✅ |

#### Pagination Parameters (GET /api/players)

| Parameter | Default | Description |
|-----------|---------|-------------|
| `pageNumber` | 0 | Page number |
| `pageSize` | 10 | Number of results per page |

Example: `GET /api/players?pageNumber=0&pageSize=5`

#### Player Request Body (POST / PUT)
```json
{
  "name": "Cristiano Ronaldo",
  "position": "Forward",
  "club": "Al Nassr"
}
```

---

### Reviews

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/api/players/{playerId}/reviews` | Get all reviews for a player | ✅ |
| GET | `/api/players/{playerId}/reviews/{id}` | Get a specific review | ✅ |
| POST | `/api/players/{playerId}/reviews` | Create a review for a player | ✅ |
| PUT | `/api/players/{playerId}/reviews/{id}` | Update a review | ✅ |
| DELETE | `/api/players/{playerId}/reviews/{id}` | Delete a review | ✅ |

#### Review Request Body (POST / PUT)
```json
{
  "authorName": "John Doe",
  "content": "Incredible player, best in the world!",
  "stars": 5
}
```

> ⚠️ `stars` must be between **1 and 5**.

---

## 📦 Project Structure

```
src/main/java/com/footballreview/api
├── controllers        # REST controllers
├── dtos               # Request and Response DTOs
├── entities           # JPA entities (Player, Review, AppUser, Role)
├── enums              # Enums (RoleName)
├── exceptions         # Custom exceptions and global handler
├── mappers            # Entity <-> DTO mappers
├── repositories       # Spring Data JPA repositories
├── security           # JWT filter, SecurityConfig, UserDetailsService
└── services           # Business logic (interfaces + implementations)
```

---

## ⚠️ Error Handling

All errors return a consistent JSON response:

```json
{
  "statusCode": 404,
  "message": "Player not found!",
  "timestamp": "2026-02-21T10:00:00.000+00:00"
}
```
