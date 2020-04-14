package com.link.todolist.Repository;

import com.link.todolist.Model.Todo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface TodoRepository extends PagingAndSortingRepository<Todo, Long> {
    Optional<Todo> findById(Long id);

    Iterable<Todo> findAll();

    Optional<Todo> findByIdAndAndStatus(Long id, boolean status);

    Todo save(Todo s);

    void deleteById(Long id);
}
