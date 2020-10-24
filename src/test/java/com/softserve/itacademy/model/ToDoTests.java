package com.softserve.itacademy.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class ToDoTests {
    @Test
    void todoWithEmptyTitle() {
        ToDo emptyTodo = new ToDo();
        emptyTodo.setTitle("");
        LocalDateTime localDateTime = LocalDateTime.now();

    }
}
