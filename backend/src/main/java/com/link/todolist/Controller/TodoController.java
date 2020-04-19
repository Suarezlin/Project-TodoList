package com.link.todolist.Controller;

import com.link.todolist.Model.Todo;
import com.link.todolist.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/todo")
@CrossOrigin(origins="*")
public class TodoController {

    @Autowired
    TodoRepository todoRepository;

    @GetMapping("/")
    public ResponseEntity<Iterable<Todo>> getAllTodo() {
        return new ResponseEntity<>(todoRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<Iterable<Todo>> getPagedTodo(@PathVariable int page) {
        if (page < 0) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        PageRequest p = PageRequest.of(page, 10, Sort.by("id").descending());

        return new ResponseEntity<>(todoRepository.findAll(p).getContent(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        Optional<Todo> todo = todoRepository.findById(id);

        if (todo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(todo.get(), HttpStatus.OK);
    }

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo) {
        todo = todoRepository.save(todo);

        return new ResponseEntity<>(todo, HttpStatus.CREATED);
    }

    @PutMapping(value = "/", consumes = "application/json")
    public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo) {
        Long id = todo.getId();

        if (id == null || todoRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        todo = todoRepository.save(todo);

        return new ResponseEntity<>(todo, HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteTodo(@PathVariable Long id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);

        if (optionalTodo.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        todoRepository.deleteById(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
