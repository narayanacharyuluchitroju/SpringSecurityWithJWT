# 🔐 SpringSecurityWithJWT

This repository demonstrates how to implement **JWT (JSON Web Token)** based authentication in a Spring Boot 3 application using **Spring Security 6**. It includes login, token generation, JWT validation, and protection of REST endpoints using custom filters.

---

## 📚 Features

- JWT Token Generation & Validation
- Stateless Authentication with Spring Security 6
- Custom JWT Filter using `OncePerRequestFilter`
- Secure REST APIs with `HttpSecurity`
- User authentication with `UserDetailsService`
- Token verification on each request
- No session creation (stateless)

---

## 🛠️ Tech Stack

- Java 17+
- Spring Boot 3
- Spring Security 6
- jjwt (JSON Web Token library)
- Maven

---

## 📦 Project Structure

```
com.pract.test
├── controller         # API endpoints (login, register)
├── model              # User model/entities
├── repository         # JPA repositories
├── service
│   ├── JwtService.java           # Token generation & validation
│   └── MyUserDetailsService.java# Custom user loader
├── security
│   ├── SecurityConfig.java      # Security filter chain setup
│   └── JwtFilter.java           # Intercepts requests to validate JWT
└── MainApplication.java         # Entry point
```

---

## 🔐 JWT Token Flow

### 1. User Login – `POST /login`
- User sends `username` and `password`.
- `AuthenticationManager` authenticates using `MyUserDetailsService`.
- If valid, `JwtService.generateToken(username)` returns a signed token.

### 2. Client Stores Token
- Client receives and stores the token.
- Sends it with future requests via:

```http
Authorization: Bearer <JWT_TOKEN>
```

### 3. Secured API Request – `GET /students`
- Token is extracted and verified in `JwtFilter`.
- `JwtService.extractUsername(token)` is used to identify the user.
- If valid, Spring sets the `SecurityContext`.

---

## 🔄 Full Flow Diagram

```
Client ──> [POST /login] ──> [AuthenticationManager]
                           └──> [JwtService.generateToken()]
                                 └──> returns JWT

Client ──> [GET /students] with Header "Authorization: Bearer <JWT>"
         └──> [JwtFilter.doFilterInternal()]
               └──> extract username from JWT
               └──> get UserDetails from MyUserDetailsService
               └──> validate token (signature + expiration)
               └──> set SecurityContext
               └──> continue to endpoint (Spring checks authorization)
```

---

## ▶️ How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/narayanacharyuluchitroju/SpringSecurityWithJWT.git
   cd SpringSecurityWithJWT
   ```

2. Run the application:
   ```bash
   mvn spring-boot:run
   ```

3. Test with Postman:
   - **Login:** `POST /login`
   - **Use JWT:** Add header  
     `Authorization: Bearer <token>` for protected routes

---

## ✅ Sample Login Request

```http
POST /login
Content-Type: application/json

{
  "username": "raghav",
  "password": "raghav123"
}
```

---

## 📬 Contact

Made by **Raghavendra Chitroju**  
📧 [narayanacharyulu.chitroju@gmail.com](mailto:narayanacharyulu.chitroju@gmail.com)

---

## 📝 License

This project is open-source and available under the [MIT License](LICENSE).
