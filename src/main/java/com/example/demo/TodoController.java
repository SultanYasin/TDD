package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class TodoController {
    @Autowired
    private TodoService todoService;

    @PostMapping("/create")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        return ResponseEntity.ok(todoService.createTodo(todo));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Todo> readTodo(@PathVariable long id) {
        Todo todo = todoService.readTodo(id);
        if (todo == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(todo);
        }
    }

    @GetMapping("/findByTitle/{title}")
    public ResponseEntity<List<Todo>> findByTitle(@PathVariable String title) {
        List<Todo> foundTodos = todoService.findByTitle(title);
        if (foundTodos.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundTodos);
        }
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<Todo> findById(@PathVariable long id) {
        Todo foundTodo = todoService.findById(id);
        if (foundTodo == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundTodo);
        }
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable long id, @RequestBody Todo todo) {
        Todo updatedTodo = todoService.updateTodo(id, todo);
        if (updatedTodo == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedTodo);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable long id) {
        if (todoService.deleteTodo(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


