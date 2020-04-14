package com.link.todolist.Repository;

import org.springframework.data.repository.CrudRepository;
import com.link.todolist.Model.TodoModel;

public interface TodoRepository extends CrudRepository<TodoModel, Long> {
}
