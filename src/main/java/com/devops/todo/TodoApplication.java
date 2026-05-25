package com.devops.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * DevOps Project 1 - Todo Application
 *
 * Entry point for both:
 *   1. Standalone execution (java -jar todo-app.war)
 *   2. External Tomcat deployment (extends SpringBootServletInitializer)
 *
 * SpringBootServletInitializer is required when deploying a WAR to an
 * external servlet container such as Tomcat 10. It bootstraps the
 * Spring ApplicationContext via the servlet container's lifecycle,
 * replacing the traditional web.xml approach.
 */
@SpringBootApplication
public class TodoApplication extends SpringBootServletInitializer {

    /**
     * Required override for external WAR deployment.
     * Binds the Spring application context to the servlet container.
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TodoApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }
}
