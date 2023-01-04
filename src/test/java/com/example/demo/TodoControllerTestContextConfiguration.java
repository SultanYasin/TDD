package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


@TestConfiguration
@SpringBootTest
class TodoControllerTestContextConfiguration {

    @Bean
    public TodoController todoController() {
        return new TodoController();
    }


    @Autowired
    private TodoController todoController;

    @Test
    public void whenCreateTodo_thenTodoShouldBeCreated() {
        Todo todo = new Todo(1, "Test Todo", "This is a test todo");
        ResponseEntity<Todo> response = todoController.createTodo(todo);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(todo, response.getBody());
    }

    @Test
    public void whenReadTodo_thenTodoShouldBeReturned() {
        Todo todo = new Todo(1, "Test Todo", "This is a test todo");
        todoController.createTodo(todo);
        ResponseEntity<Todo> response = todoController.readTodo(1);
        assertEquals( HttpStatus.OK, response.getStatusCode());
        assertEquals(todo, response.getBody());
    }

    @Test
    public void whenUpdateTodo_thenTodoShouldBeUpdated() {
        Todo todo = new Todo(1, "Test Todo", "This is a test todo");
        System.out.println("\nadded");
        todoController.createTodo(todo);
        Todo updatedTodo = new Todo(1, "Updated Todo", "This is an updated todo");
        System.out.println("\nupdated");

        ResponseEntity<Todo> response = todoController.findById(1);
        System.out.println("\nbrought" + response.getStatusCode());
        response = todoController.updateTodo(1, updatedTodo);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotEquals(updatedTodo, response.getBody());
    }

    @Test
    public void whenFindById_thenTodoShouldBeReturned() {
        Todo todo1 = new Todo(1, "Test Todo", "This is a test todo");
        Todo todo2 = new Todo(2, "Another Test Todo", "This is another test todo");
        Todo todo3 = new Todo(3, "Test Todo Again", "This is yet another test todo");
        todoController.createTodo(todo1);
        todoController.createTodo(todo2);
        todoController.createTodo(todo3);
        ResponseEntity<Todo> response = todoController.findById(2);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(todo2, response.getBody());
    }


    @Test
    public void whenDeleteTodo_thenTodoShouldBeDeleted() {
        Todo todo = new Todo(1, "Test Todo", "This is a test todo");
        todoController.createTodo(todo);
        ResponseEntity<Void> deleteResponse = todoController.deleteTodo(1);
        assertEquals(HttpStatus.OK, deleteResponse.getStatusCode());
        ResponseEntity<Todo> readResponse = todoController.readTodo(1);
        assertEquals(HttpStatus.NOT_FOUND, readResponse.getStatusCode());
    }
}