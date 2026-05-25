# ─────────────────────────────────────────────────────────────────────────────
# DevOps Project 1 - Multi-Stage Dockerfile
#
# Stage 1: Build WAR using Maven + Java 17
# Stage 2: Deploy to Tomcat 10 runtime (minimal production image)
#
# Build:  docker build -t todo-app:1.0.0 .
# Run:    docker run -p 8080:8080 todo-app:1.0.0
# Access: http://localhost:8080/todo-app/
# ─────────────────────────────────────────────────────────────────────────────

# ── Stage 1: Build ────────────────────────────────────────────────────────────
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /build

# Copy pom.xml first to leverage Docker layer caching for dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -q

# Copy source and build
COPY src ./src
RUN mvn clean package -DskipTests -q

# ── Stage 2: Runtime ──────────────────────────────────────────────────────────
FROM tomcat:10.1-jdk17

LABEL maintainer="DevOps Engineer"
LABEL project="devops-project-1"
LABEL version="1.0.0"

# Remove default Tomcat example apps to keep image clean
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy WAR from build stage
COPY --from=builder /build/target/todo-app.war /usr/local/tomcat/webapps/todo-app.war

# Expose Tomcat port
EXPOSE 8080

# Tomcat entrypoint (default)
CMD ["catalina.sh", "run"]
