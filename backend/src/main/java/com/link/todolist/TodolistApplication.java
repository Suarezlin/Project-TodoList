package com.link.todolist;

import com.link.todolist.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TodolistApplication {

    @Autowired
    TodoRepository todoRepository;

    public static void main(String[] args) {
        SpringApplication.run(TodolistApplication.class, args);
    }

}
