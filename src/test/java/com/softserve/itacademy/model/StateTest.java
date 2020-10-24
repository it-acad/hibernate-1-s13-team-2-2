package com.softserve.itacademy.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StateTest {

    @Test
    void constraintViolationOnEmptyRoleName() {
        State emptyState = new State();
        emptyState.setName("");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<State>> violations = validator.validate(emptyState);
        assertEquals(2, violations.size());
    }

    @Test
    void roleWithCorrectName() {
        State emptyState = new State();
        emptyState.setName("Correct");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<State>> violations = validator.validate(emptyState);
        assertEquals(0, violations.size());
    }

    @Test
    void roleWithCorrectUser() {
        State emptyState = new State();
        Task task = new Task();
        emptyState.setName("Name");
        emptyState.setTasks(List.of(task));


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<State>> violations = validator.validate(emptyState);
        assertEquals(0, violations.size());
        assertEquals("Name", emptyState.getName());
        assertEquals(List.of(task), emptyState.getTasks());
    }
}
