# 💰 Finance Data Processing and Access Control Backend

A Spring Boot backend application designed to manage financial records with role-based access control and dashboard analytics.

---

## 🚀 Tech Stack
- Java 17+
- Spring Boot
- Spring Security (Session-based authentication)
- MySQL
- Spring Data JPA (Hibernate)
- Lombok

---

## 📌 Features

### 🔐 Authentication & Authorization
- User registration with role assignment
- Secure login using Spring Security
- Session-based authentication (no JWT)
- Role-Based Access Control (RBAC)

### 👥 Role Management
- **ADMIN**: Full access (Admin + Finance + Dashboard)
- **ANALYST**: Finance + Dashboard access
- **VIEWER**: Read-only Dashboard access

### 💰 Finance Management
- Create financial records (income/expense)
- Update and delete records
- Fetch all records
- Filter by:
  - Type (INCOME / EXPENSE)
  - Category
  - Date range

### 📊 Dashboard Analytics
- Total Income
- Total Expense
- Net Balance
- Category-wise summary
- Monthly trends
- Recent transactions

---

## 🔐 Roles & Permissions

| Role     | Dashboard | Finance | Admin |
|----------|----------|---------|-------|
| ADMIN    | ✅       | ✅      | ✅    |
| ANALYST  | ✅       | ✅      | ❌    |
| VIEWER   | ✅       | ❌      | ❌    |

---

## 📊 API Endpoints

### 🔑 Authentication
POST /auth/register
POST /login
GET /logout

---

### 💰 Finance APIs
POST /finance
GET /finance
PUT /finance/{id}
DELETE /finance/{id}

GET /finance/type/{type}
GET /finance/category/{category}
GET /finance/date?start={startDate}&end={endDate}

---

### 📊 Dashboard APIs
GET /dashboard/summary
GET /dashboard/category
GET /dashboard/trends
GET /dashboard/recent

---

### 🛡️ Admin API
GET /admin

---

## 🧠 Architecture & Design

- Feature-based modular structure
- Layered architecture:
  - Controller → Service → Repository
- DTO pattern for request/response handling
- Centralized exception handling
- Clean separation of concerns

---

## 🗄️ Database Configuration

### Create Database:
```sql
CREATE DATABASE finance_db;
```
Update application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/finance_db
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

▶️ How to Run
Clone the repository
Configure MySQL database
Update application.properties
Run the application

🧪 Testing (Postman)
Steps:
Register user (/auth/register)
Login (/login)
Use APIs based on role
Logout (/logout) before switching users

🧑‍💻 Author
Pritish Dighore
