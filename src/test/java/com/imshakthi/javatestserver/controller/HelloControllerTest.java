package com.imshakthi.javatestserver.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HelloControllerTest {
    private HelloController testController = new HelloController();

    @Test
    void shouldReturnIndexPage() {
        String actual = testController.index();

        Assertions.assertEquals("Welcome to HOME page!", actual);
    }
}