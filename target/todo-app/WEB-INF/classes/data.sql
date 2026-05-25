-- ─────────────────────────────────────────────────────────────
-- DevOps Project 1 - Sample Data
-- Loaded by Spring Boot on every startup (H2 in-memory)
-- ─────────────────────────────────────────────────────────────

INSERT INTO todos (title, description, priority, completed, created_at, updated_at) VALUES
('Set up CI/CD Pipeline', 'Configure Jenkins or GitHub Actions for automated build and deployment', 'HIGH', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Write Dockerfile', 'Containerise the application with a multi-stage Docker build', 'HIGH', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Configure Tomcat 10', 'Deploy WAR file to external Tomcat, configure server.xml and context.xml', 'HIGH', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Set up Maven Build', 'Configure pom.xml with correct dependencies, plugins and WAR packaging', 'MEDIUM', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Write Unit Tests', 'Add JUnit 5 tests for service layer with MockMvc for controllers', 'MEDIUM', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Configure Application Monitoring', 'Integrate Spring Actuator with Prometheus and Grafana dashboards', 'MEDIUM', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Infrastructure as Code', 'Write Terraform or Ansible scripts to provision EC2 and configure Tomcat', 'LOW', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Security Hardening', 'Disable H2 console in production, configure HTTPS, add Spring Security', 'HIGH', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
