# SpringSecurityWithJWT
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

JWT Token Flow: Generation → Authentication
1. User Login (POST /login)
User submits username & password to /login endpoint.

AuthenticationManager authenticates using MyUserDetailsService.

If credentials are correct:

JwtService.generateToken(username) is called.

A JWT token is generated with:

subject: username

issuedAt + expiration

signed using HMAC-SHA256 and a generated secret key

Token is returned in the response.

2. Client Stores Token
Client (browser, Postman, frontend) receives token

It stores it in:

Authorization: Bearer <JWT_TOKEN> header for all future requests

. Secured API Request (e.g., GET /students)
Incoming request has:

makefile
Copy
Edit
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR...
4. JwtFilter Intercepts the Request
Reads Authorization header

Extracts the token from Bearer ...

Calls jwtService.extractUsername(token)

Verifies token signature and extracts subject (username)

5. UserDetails Loading & Validation
Checks that SecurityContext is not already authenticated

Loads user using:

java
Copy
Edit
context.getBean(MyUserDetailsService.class).loadUserByUsername(username);
Validates token:

Username matches

Token is not expired

6. Authentication Success
If valid:

Creates UsernamePasswordAuthenticationToken

Sets it in SecurityContextHolder

Request continues with authenticated context

7. Spring Security Authorization
Now Spring Security allows access to secured endpoints

Based on roles or just general .authenticated()
