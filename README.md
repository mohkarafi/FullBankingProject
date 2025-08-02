## FULL-BANKING-APPLICATION 🏦 - Backend API

FULL-BANKING-APPLICATION is a Spring Boot backend API designed to help banks or fintechs manage users, transactions,
accounts, and roles securely and efficiently.

## Features

User Management
Register, update, delete, and list users with assigned roles.

Authentication & Authorization
Secure login system with JWT tokens; role-based access control for users and admins.

Account Management
Create and manage bank accounts for users.

Transaction Processing
Record deposits, withdrawals, and transfers between accounts.

Transaction History
Retrieve and export account statements.

Roles & Permissions
Define roles (e.g., USER, ADMIN) and restrict endpoint access accordingly.

API Documentation
Easily explore REST endpoints using Swagger UI.

## Tech Stack

Layer Technology
Backend Spring Boot
Build Tool Maven
Database MySQL
Security Spring Security, JWT
API Docs Swagger
Containerization Docker, Docker Compose

## 🚀 How to Run

bash
Copy
Edit

# Clone the repository

git clone https://github.com/mohkarafi/FullBankingApplication.git

# Navigate into the project directory

cd FullBankingApplication

# Build and start with Docker

docker-compose up --build
The API will be available at: http://localhost:8060

## Security Notes

✅ All endpoints are secured using Spring Security.

✅ Authentication is handled via JWT tokens.

✅ Access is role-based (e.g., USER, ADMIN).

🔗 API Documentation
Once the app is running, access the API documentation at:
http://localhost:8060/swagger-ui.html

👤 Author
LinkedIn: Mohamed Karafi

GitHub: @mohkarafi

📧 karafimhd@gmail.com