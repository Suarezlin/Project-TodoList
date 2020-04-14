package com.link.todolist;

import com.link.todolist.Model.Todo;
import com.link.todolist.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TodolistApplication {

    @Autowired
    TodoRepository todoRepository;

    public static void main(String[] args) {
        SpringApplication.run(TodolistApplication.class, args);
    }

    @GetMapping("/")
    public Iterable<Todo> test() {
        return todoRepository.findAll();
    }
}
