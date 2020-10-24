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

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ToDoTests {

    private static ToDo validToDo;
    private static User user;
    private static Role traineeRole;
    private static Task task;
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

        task = new Task();
        task.setName("Valid task");
        task.setPriority(Priority.HIGH);

        validToDo = new ToDo();
        validToDo.setTitle("Title");
        validToDo.setOwner(user);
        validToDo.setTasks(List.of(task));

        localDateTime = LocalDateTime.of(2020, 11, 11, 10, 10, 10);
    }

    @Test
    void createValidToDo() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ToDo>> violations = validator.validate(validToDo);

        assertEquals(0, violations.size());
    }

    @Test
    void rightInitializingToDo() {
        ToDo toDo = new ToDo();
        toDo.setTitle("ToDo");
        toDo.setOwner(user);
        toDo.setCreatedAt(localDateTime);
        toDo.setTasks(List.of(task));
        toDo.setCollaborators(List.of(user));

        assertEquals("ToDo", toDo.getTitle());
        assertEquals(user, toDo.getOwner());
        assertEquals(localDateTime, toDo.getCreatedAt());
        assertEquals(List.of(task), toDo.getTasks());
        assertEquals(List.of(user), toDo.getCollaborators());
    }

    @Test
    void createTodoWithEmptyTitle() {
        ToDo newToDo = new ToDo();
        newToDo.setTitle("");
        newToDo.setOwner(user);
        newToDo.setTasks(List.of(task));
        newToDo.setCollaborators(List.of(user));
        newToDo.setId(11);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ToDo>> violations = validator.validate(newToDo);

        assertEquals(1, violations.size());
        assertEquals(11, newToDo.getId());
    }
}
