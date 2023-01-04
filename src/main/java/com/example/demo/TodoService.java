package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private List<Todo> todos = new ArrayList<>();

    public Todo createTodo(Todo todo) {
        todo.setId(todos.size() + 1);
        todos.add(todo);
        return todo;
    }

    public Todo readTodo(long id) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
                return todo;
            }
        }
        return null;
    }

    public Todo updateTodo(long id, Todo todo) {
        Todo existingTodo = readTodo(id);
        if (existingTodo == null) {
            return null;
        } else {
            int index = todos.indexOf(existingTodo);
            Todo updatedTodo = new Todo(id, todo.getTitle(), todo.getDescription());
            todos.set(index, updatedTodo);
            return updatedTodo;
        }
    }


    public boolean deleteTodo(long id) {
        Todo todo = readTodo(id);
        if (todo == null) {
            return false;
        } else {
            todos.remove(todo);
            return true;
        }
    }

    public List<Todo> findByTitle(String title) {
        List<Todo> foundTodos = new ArrayList<>();
        for (Todo todo : todos) {
            if (todo.getTitle().contains(title)) {
                foundTodos.add(todo);
            }
        }
        return foundTodos;
    }


    public Todo findById(long id) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
                return todo;
            }
        }
        return null;
    }
}
