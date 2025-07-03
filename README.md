# 📝 Note Keeper API
A secure, minimalistic Spring Boot REST API that lets authenticated users create, view, update, and delete notes.
Built with JWT Authentication, Spring Security, and Spring Data JPA.

# 📁 Project Structure

![image](https://github.com/user-attachments/assets/c587a861-23df-47f1-bc5c-ea8a9994fb9d)


# ⚙️ Tech Stack
Java 17

Spring Boot 3.5.3

Spring Security + JWT

Spring Data JPA

H2 (in-memory DB)

Lombok

Maven

# 🚀 Running Locally
## 1. Clone the repo
git clone https://github.com/danishfaizan8/note-api.git

cd note-api

## 2. Build and Run
```
./mvnw spring-boot:run
```
🔄 The app will run on:
```
http://localhost:8081
```
# 🔐 Authentication Flow
## ➕ Register

POST /auth/register
## Body (JSON):
```
{
  "username": "john",
  "password": "secret123"
}
```

## 🔑 Login
POST /auth/login
## Body (JSON):
```
{
  "username": "john",
  "password": "secret123"
}
```
## Response:
```
{
  "token": "eyJhbGciOiJIUzI1NiIsInR..."
}
```
## ✅ Save this token and add it in Postman → Headers:
Authorization: Bearer <token>

# 📝 Notes API (Requires Auth)
## ➕ Create Note
POST /notes

Headers: Authorization: Bearer <token>
Body:
```
{
  "title": "First Note",
  "content": "This is my first secure note!"
}
```
## 📖 Get All Notes

GET /notes

## Returns:
```
[
  {
    "id": 1,
    "title": "First Note",
    "content": "This is my first secure note!"
  }
]
```

## ✏️ Update Note
```
PUT /notes/{id}
```

Example: PUT /notes/1

Body:
```
{
  "title": "Updated Title",
  "content": "Today I built Note Keeper API with JWT and validation!"
}
```
## ❌ Delete Note
```
DELETE /notes/{id}
```
Example: DELETE /notes/1

# ✅ Sample Test Run Steps (Postman)

Register:

POST http://localhost:8081/auth/register 
→ Save user

Login:

POST http://localhost:8081/auth/login 

→ Copy JWT token

Create Note:

POST http://localhost:8081/notes

Add Authorization: Bearer <token> header

View Notes:

GET http://localhost:8081/notes

Update Note:

PUT http://localhost:8081/notes/1

Delete Note:

DELETE http://localhost:8081/notes/1

## 🧪 Running Tests
```
./mvnw test

```

