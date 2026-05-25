package com.devops.todo;

import com.devops.todo.model.Todo;
import com.devops.todo.repository.TodoRepository;
import com.devops.todo.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("TodoService Unit Tests")
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    private Todo sampleTodo;

    @BeforeEach
    void setUp() {
        sampleTodo = new Todo("Set up CI/CD", "Configure Jenkins pipeline", "HIGH");
    }

    @Test
    @DisplayName("Should return all todos")
    void shouldReturnAllTodos() {
        when(todoRepository.findAll()).thenReturn(List.of(sampleTodo));
        List<Todo> result = todoService.getAllTodos();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Set up CI/CD");
    }

    @Test
    @DisplayName("Should create todo and persist it")
    void shouldCreateTodo() {
        when(todoRepository.save(any(Todo.class))).thenReturn(sampleTodo);
        Todo created = todoService.createTodo(sampleTodo);
        assertThat(created.getTitle()).isEqualTo("Set up CI/CD");
        verify(todoRepository, times(1)).save(sampleTodo);
    }

    @Test
    @DisplayName("Should toggle completed status")
    void shouldToggleCompleted() {
        sampleTodo.setCompleted(false);
        when(todoRepository.findById(1L)).thenReturn(Optional.of(sampleTodo));
        when(todoRepository.save(any())).thenReturn(sampleTodo);
        boolean result = todoService.toggleComplete(1L);
        assertThat(result).isTrue();
        assertThat(sampleTodo.isCompleted()).isTrue();
    }

    @Test
    @DisplayName("Should return false when toggling non-existent todo")
    void shouldReturnFalseOnMissingToggle() {
        when(todoRepository.findById(99L)).thenReturn(Optional.empty());
        boolean result = todoService.toggleComplete(99L);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Should delete todo when it exists")
    void shouldDeleteExistingTodo() {
        when(todoRepository.existsById(1L)).thenReturn(true);
        boolean result = todoService.deleteTodo(1L);
        assertThat(result).isTrue();
        verify(todoRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Should return stats with correct counts")
    void shouldReturnStats() {
        when(todoRepository.count()).thenReturn(5L);
        when(todoRepository.countCompleted()).thenReturn(3L);
        when(todoRepository.countPending()).thenReturn(2L);
        TodoService.TodoStats stats = todoService.getStats();
        assertThat(stats.total()).isEqualTo(5L);
        assertThat(stats.completed()).isEqualTo(3L);
        assertThat(stats.pending()).isEqualTo(2L);
    }
}
