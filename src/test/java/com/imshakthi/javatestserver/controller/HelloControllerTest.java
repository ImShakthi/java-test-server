package com.imshakthi.javatestserver.controller;

import com.imshakthi.javatestserver.model.response.MessageBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloControllerTest {
    private final HelloController testController = new HelloController();

    @Test
    void shouldReturnIndexPage() {
        ResponseEntity<MessageBody> actual = testController.index();
        MessageBody expected = MessageBody.builder().message("Welcome to HOME page!").build();

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals(expected, actual.getBody());
    }
}