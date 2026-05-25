package com.devops.todo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Serves the application root and exposes a /api/info endpoint
 * used by the front-end to display live application metadata.
 */
@Controller
public class HomeController {

    @Value("${spring.application.name:DevOps Todo App}")
    private String appName;

    @Value("${app.version:1.0.0}")
    private String appVersion;

    /**
     * Serves the static index.html from src/main/resources/static/
     */
    @GetMapping("/")
    public String index() {
        return "forward:/index.html";
    }

    /**
     * Live application info consumed by the front-end dashboard.
     */
    @GetMapping("/api/info")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> appInfo() {
        Map<String, Object> info = new LinkedHashMap<>();
        info.put("project", "DevOps Project 1");
        info.put("name", appName);
        info.put("version", appVersion);
        info.put("javaVersion", System.getProperty("java.version"));
        info.put("springBootVersion", "3.2.5");
        info.put("buildTool", "Apache Maven 3.x");
        info.put("packaging", "WAR");
        info.put("serverTime", LocalDateTime.now().toString());
        info.put("status", "RUNNING");
        info.put("endpoints", List.of(
            "GET  /api/todos",
            "GET  /api/todos/{id}",
            "POST /api/todos",
            "PUT  /api/todos/{id}",
            "DELETE /api/todos/{id}",
            "PATCH /api/todos/{id}/toggle",
            "GET  /api/todos/stats",
            "GET  /api/todos/filter?completed=true",
            "GET  /api/info",
            "GET  /actuator/health"
        ));
        return ResponseEntity.ok(info);
    }
}
