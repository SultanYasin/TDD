package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfiguration
@SpringBootTest
class TodoServiceTestContextConfiguration {

    @Autowired
    TodoService todoService;

    @Test
    public void whenCreateTodo_thenTodoShouldBeCreated() {
        Todo todo = new Todo(1, "Test Todo", "This is a test todo");
        Todo createdTodo = todoService.createTodo(todo);
        assertEquals(todo, createdTodo);
    }

    @Test
    public void whenReadTodo_thenTodoShouldBeReturned() {
        Todo todo = new Todo(1, "Test Todo", "This is a test todo");
        todoService.createTodo(todo);
        Todo readTodo = todoService.readTodo(1);
        assertEquals(todo, readTodo);
    }

    @Test
    public void whenUpdateTodo_thenTodoShouldBeUpdated() {
        Todo todo = new Todo(1, "Test Todo", "This is a test todo");
        todoService.createTodo(todo);
        Todo updatedTodo = new Todo(1, "Updated Todo", "This is an updated todo");
        Todo returnedTodo = todoService.updateTodo(1, updatedTodo);
        assertNotEquals(updatedTodo, returnedTodo);
    }

    @Test
    public void whenDeleteTodo_thenTodoShouldBeDeleted() {
        Todo todo = new Todo(1, "Test Todo", "This is a test todo");
        todoService.createTodo(todo);
        todoService.deleteTodo(1);
        Todo deletedTodo = todoService.readTodo(1);
        assertNull(deletedTodo);
    }

    @Test
    public void whenFindByTitle_thenTodoListShouldBeReturned() {
        Todo todo1 = new Todo(1, "Test1", "This is a test todo");
        Todo todo2 = new Todo(2, "Test2", "This is another test todo");
        Todo todo3 = new Todo(3, "Test3", "This is yet another test todo");
        todoService.createTodo(todo1);
        todoService.createTodo(todo2);
        todoService.createTodo(todo3);
        List<Todo> foundTodos = todoService.findByTitle("Test2");
        assertEquals(1, foundTodos.size());
        assertTrue(foundTodos.contains(todo2));
    }

    @Test
    public void whenFindById_thenTodoShouldBeReturned() {
        Todo todo1 = new Todo(1, "Test Todo", "This is a test todo");
        Todo todo2 = new Todo(2, "Another Test Todo", "This is another test todo");
        Todo todo3 = new Todo(3, "Test Todo Again", "This is yet another test todo");
        todoService.createTodo(todo1);
        todoService.createTodo(todo2);
        todoService.createTodo(todo3);
        Todo foundTodo = todoService.findById(2);
        assertEquals(todo2, foundTodo);
    }
}
