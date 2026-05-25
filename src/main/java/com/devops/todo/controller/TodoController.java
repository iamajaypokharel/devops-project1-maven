package com.devops.todo.controller;

import com.devops.todo.model.Todo;
import com.devops.todo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST Controller exposing Todo CRUD endpoints.
 *
 * Base path: /api/todos
 *
 * Endpoints:
 *   GET    /api/todos              - list all todos
 *   GET    /api/todos/{id}         - get single todo
 *   POST   /api/todos              - create todo
 *   PUT    /api/todos/{id}         - update todo
 *   DELETE /api/todos/{id}         - delete todo
 *   PATCH  /api/todos/{id}/toggle  - toggle complete status
 *   GET    /api/todos/stats        - get aggregate stats
 *   GET    /api/todos/filter       - filter by status or priority
 */
@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "*")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        return ResponseEntity.ok(todoService.getAllTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        return todoService.getTodoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@Valid @RequestBody Todo todo) {
        Todo created = todoService.createTodo(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id,
                                           @Valid @RequestBody Todo todo) {
        return todoService.updateTodo(id, todo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        return todoService.deleteTodo(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/toggle")
    public ResponseEntity<Map<String, Object>> toggleComplete(@PathVariable Long id) {
        boolean success = todoService.toggleComplete(id);
        if (!success) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(Map.of("message", "Status toggled successfully", "id", id));
    }

    @GetMapping("/stats")
    public ResponseEntity<TodoService.TodoStats> getStats() {
        return ResponseEntity.ok(todoService.getStats());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Todo>> filterTodos(
            @RequestParam(required = false) Boolean completed,
            @RequestParam(required = false) String priority) {

        if (completed != null) {
            return ResponseEntity.ok(todoService.getTodosByStatus(completed));
        }
        if (priority != null) {
            return ResponseEntity.ok(todoService.getTodosByPriority(priority));
        }
        return ResponseEntity.ok(todoService.getAllTodos());
    }
}
