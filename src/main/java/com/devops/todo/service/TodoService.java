package com.devops.todo.service;

import com.devops.todo.model.Todo;
import com.devops.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service layer encapsulating all Todo business logic.
 * Controllers depend on this; repository is not accessed directly from controllers.
 */
@Service
@Transactional
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Optional<Todo> updateTodo(Long id, Todo updatedTodo) {
        return todoRepository.findById(id).map(existing -> {
            existing.setTitle(updatedTodo.getTitle());
            existing.setDescription(updatedTodo.getDescription());
            existing.setPriority(updatedTodo.getPriority());
            existing.setCompleted(updatedTodo.isCompleted());
            return todoRepository.save(existing);
        });
    }

    public boolean toggleComplete(Long id) {
        return todoRepository.findById(id).map(todo -> {
            todo.setCompleted(!todo.isCompleted());
            todoRepository.save(todo);
            return true;
        }).orElse(false);
    }

    public boolean deleteTodo(Long id) {
        if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Todo> getTodosByStatus(boolean completed) {
        return todoRepository.findByCompleted(completed);
    }

    public List<Todo> getTodosByPriority(String priority) {
        return todoRepository.findByPriority(priority.toUpperCase());
    }

    public TodoStats getStats() {
        long total = todoRepository.count();
        long completed = todoRepository.countCompleted();
        long pending = todoRepository.countPending();
        return new TodoStats(total, completed, pending);
    }

    public record TodoStats(long total, long completed, long pending) {}
}
