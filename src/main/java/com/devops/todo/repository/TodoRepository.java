package com.devops.todo.repository;

import com.devops.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for Todo entities.
 * JpaRepository provides full CRUD + pagination out of the box.
 */
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByCompleted(boolean completed);

    List<Todo> findByPriority(String priority);

    List<Todo> findByCompletedOrderByCreatedAtDesc(boolean completed);

    @Query("SELECT COUNT(t) FROM Todo t WHERE t.completed = true")
    long countCompleted();

    @Query("SELECT COUNT(t) FROM Todo t WHERE t.completed = false")
    long countPending();
}
