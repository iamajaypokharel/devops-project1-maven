
# devops-project1-maven
# DevOps Project 1 — Java Todo Application

> A production-grade Java web application demonstrating the full DevOps lifecycle.
> Built with Spring Boot 3.x, Maven, and deployed to Apache Tomcat 10.

---

## Project Overview

| Attribute       | Value                              |
|-----------------|------------------------------------|
| Project         | DevOps Project 1                   |
| Type            | Java Web Application (WAR)         |
| Framework       | Spring Boot 3.2.5                  |
| Java Version    | 17 LTS                             |
| Build Tool      | Apache Maven 3.x                   |
| Server          | Apache Tomcat 10.x                 |
| Database        | H2 In-Memory (dev) / swap for prod |
| Packaging       | WAR                                |

---

## Prerequisites

- Java 17+ (`java -version`)
- Apache Maven 3.8+ (`mvn -version`)
- Apache Tomcat 10.x (for external deployment)

> **Critical:** Spring Boot 3.x uses the `jakarta.*` namespace (Jakarta EE 10).
> Tomcat 10+ is required. Tomcat 9 uses `javax.*` and will fail at deployment.

---

## Quick Start

### 1. Clone and Build

```bash
git clone <repo-url>
cd devops-project1
mvn clean package -DskipTests
ls -lh target/todo-app.war   # Should be ~15MB
```

### 2. Deploy to External Tomcat

```bash
sudo systemctl stop tomcat10
sudo cp target/todo-app.war /var/lib/tomcat10/webapps/
sudo systemctl start tomcat10
sudo tail -f /var/log/tomcat10/catalina.out
```

### 3. Verify Deployment

```bash
# Health check
curl http://localhost:8080/todo-app/actuator/health

# List todos
curl http://localhost:8080/todo-app/api/todos

# Create a todo
curl -X POST http://localhost:8080/todo-app/api/todos \
  -H "Content-Type: application/json" \
  -d '{"title":"Test deployment","priority":"HIGH"}'
```

---

## Run with Docker

```bash
# Build image
docker build -t todo-app:1.0.0 .

# Run container
docker run -p 8080:8080 todo-app:1.0.0

# Access
curl http://localhost:8080/todo-app/actuator/health
```

---

## REST API Endpoints

| Method | Endpoint                          | Description                     |
|--------|-----------------------------------|---------------------------------|
| GET    | `/api/todos`                      | List all todos                  |
| GET    | `/api/todos/{id}`                 | Get todo by ID                  |
| POST   | `/api/todos`                      | Create todo                     |
| PUT    | `/api/todos/{id}`                 | Update todo                     |
| DELETE | `/api/todos/{id}`                 | Delete todo                     |
| PATCH  | `/api/todos/{id}/toggle`          | Toggle completed status         |
| GET    | `/api/todos/stats`                | Aggregate statistics            |
| GET    | `/api/todos/filter?completed=true`| Filter by status or priority    |
| GET    | `/api/info`                       | Application runtime info        |
| GET    | `/actuator/health`                | Spring Actuator health check    |
| GET    | `/h2-console`                     | H2 database console (dev only)  |

---

## Project Structure

```
devops-project1/
├── Dockerfile
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   ├── java/com/devops/todo/
    │   │   ├── TodoApplication.java          ← Main + SpringBootServletInitializer
    │   │   ├── controller/
    │   │   │   ├── HomeController.java       ← Serves index.html + /api/info
    │   │   │   └── TodoController.java       ← REST CRUD endpoints
    │   │   ├── model/
    │   │   │   └── Todo.java                 ← JPA entity
    │   │   ├── repository/
    │   │   │   └── TodoRepository.java       ← Spring Data JPA
    │   │   └── service/
    │   │       └── TodoService.java          ← Business logic layer
    │   └── resources/
    │       ├── application.properties
    │       ├── data.sql                      ← Sample data on startup
    │       └── static/
    │           └── index.html               ← Front-end dashboard
    └── test/
        └── java/com/devops/todo/
            └── TodoServiceTest.java          ← JUnit 5 unit tests
```

---

## Maven Build Lifecycle

```
mvn clean package
  │
  ├── validate    → checks pom.xml integrity
  ├── compile     → javac compiles src/main/java → .class files
  ├── test        → runs src/test/java with JUnit 5
  ├── package     → maven-war-plugin packages → target/todo-app.war
  └── (install / deploy for CI/CD pipelines)
```

---

## DevOps Production Checklist

- [ ] Disable H2 console: `spring.h2.console.enabled=false`
- [ ] Replace H2 with PostgreSQL/MySQL via `spring.datasource.*`
- [ ] Set `spring.jpa.hibernate.ddl-auto=validate` (not `create-drop`)
- [ ] Restrict Actuator endpoints: `management.endpoints.web.exposure.include=health`
- [ ] Configure HTTPS on Tomcat (SSL connector in `server.xml`)
- [ ] Externalise config via environment variables or Spring Cloud Config
- [ ] Add Spring Security for API authentication
- [ ] Integrate `/actuator/metrics` with Prometheus + Grafana
- [ ] Add CI/CD pipeline (Jenkins / GitHub Actions)

---

## Running Tests

```bash
mvn test
# Generates reports at: target/surefire-reports/
```
# Todo App (Maven + Spring Boot + Tomcat Deployment)

## 📌 Project Overview
This is a simple Todo web application built using **Java, Maven, and Spring Boot**, deployed on **Apache Tomcat 10** in an Ubuntu AWS EC2 instance.

---

## ⚙️ Tech Stack
- Java
- Maven
- Spring Boot
- Apache Tomcat 10
- Ubuntu (AWS EC2)

---

## 🚀 Build Process

### 1. Clone Repository
```bash
git clone https://github.com/your-username/todo-app.git
cd todo-app

---

## 🔧 Prerequisites
Before running this project, make sure you have:

- Java installed
- Maven installed
- Apache Tomcat 10 installed
- Git installed

Check versions:

```bash
java -version
mvn -version




🚀 Step 1: Clone Repository
git clone https://github.com/your-username/devops-project1-maven.git
cd devops-project1-maven
🚀 Step 2: Build Project using Maven
mvn clean package
📌 What this does:
clean → removes old build files
package → compiles and creates .war file
📦 Output:
target/todo-app.war
🚀 Step 3: Deploy to Tomcat

Copy WAR file to Tomcat webapps folder:

sudo cp target/todo-app.war /var/lib/tomcat10/webapps/

Restart Tomcat:

sudo systemctl restart tomcat10
🌐 Step 4: Access Application

Open browser:

http://<your-ec2-public-ip>:8080/todo-app

Example:

http://13.63.xx.xx:8080/todo-app
📌 Git Commands Used
git init
git add .
git commit -m "Initial commit - Maven Todo App"
git branch -M main
git remote add origin https://github.com/your-username/devops-project1-maven.git
git push -u origin main
📦 Build Output Summary

After successful build:

WAR file generated → target/todo-app.war
Deployed to Tomcat
Application running on EC2 server




---

# 🎯 What you just got
This README is:
- ✅ Professional DevOps style
- ✅ GitHub ready
- ✅ Step-by-step full workflow
- ✅ Beginner + industry level
- ✅ Clean and structured

👨‍💻 Author

Ajay Pokharel
e43950d (initial commit-maven todo app)
