package com.softserve.itacademy.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskTest {

    private static Role traineeRole;
    private static User user;
    private static State state;
    private static ToDo toDo;
    private static List<Task> tasks;
    private static Task validTask;
    private static LocalDateTime localDateTime;

    @BeforeAll
    static void init() {
        traineeRole = new Role();
        traineeRole.setName("TRAINEE");

        user = new User();
        user.setEmail("valid@cv.edu.ua");
        user.setFirstName("Valid-Name");
        user.setLastName("Valid-Name");
        user.setPassword("qwQW12!@");
        user.setRole(traineeRole);

        localDateTime = LocalDateTime.of(2020, 11, 11, 10, 10, 10);

        toDo = new ToDo();
        toDo.setTitle("Title");
        toDo.setOwner(user);
        toDo.setCreatedAt(localDateTime);

        tasks = new ArrayList<>();

        state = new State();
        state.setName("First");
        state.setTasks(tasks);

        validTask = new Task();
        validTask.setName("Valid task");
        validTask.setPriority(Priority.HIGH);
        validTask.setTodo(toDo);
        validTask.setState(state);
    }

    @Test
    void createValidTask() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Task>> violations = validator.validate(validTask);

        assertEquals(0, violations.size());
    }

    @Test
    void rightInitializingTask() {
        Task task = new Task();
        task.setName("Go home");
        task.setPriority(Priority.HIGH);
        task.setState(state);
        task.setTodo(toDo);

        assertEquals("Go home", task.getName());
        assertEquals(Priority.HIGH, task.getPriority());
        assertEquals(state, task.getState());
        assertEquals(toDo, task.getTodo());
    }

    @Test
    void wrongInitializingTask() {
        Task task = new Task();
        task.setName("Go home");
        task.setPriority(Priority.HIGH);
        task.setState(state);
        task.setTodo(toDo);

        assertEquals("Go home", task.getName());
        assertEquals(Priority.HIGH, task.getPriority());
        assertEquals(state, task.getState());
        assertEquals(toDo, task.getTodo());
    }

    @Test
    void createTaskWithEmptyName() {
        Task task = new Task();
        task.setName("");
        task.setPriority(Priority.HIGH);
        task.setState(state);
        task.setTodo(toDo);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Task>> violations = validator.validate(task);

        assertEquals(2, violations.size());
    }

    @Test
    void createTaskWithNullName() {
        Task task = new Task();
        task.setName(null);
        task.setPriority(Priority.HIGH);
        task.setState(state);
        task.setTodo(toDo);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Task>> violations = validator.validate(task);

        assertEquals(1, violations.size());
    }

    @Test
    void createTaskWithNullPriority() {
        Task task = new Task();
        task.setName("Go home");
        task.setPriority(null);
        task.setState(state);
        task.setTodo(toDo);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Task>> violations = validator.validate(task);

        assertEquals(1, violations.size());
    }

    @Test
    void testToString() {
        String expected = "Task{id=0, name='Valid task', priority='HIGH', todo=ToDo{id=0, title='Title', " +
                "createdAt=2020-11-11T10:10:10, owner=User{id=0, firstName='Valid-Name', lastName='Valid-Name', " +
                "email='valid@cv.edu.ua', password='qwQW12!@', role=Role {id = 0, name = 'TRAINEE'} }}, " +
                "state=State{id=0, name='First'}}";
        assertEquals(validTask.toString(),expected);
    }

}