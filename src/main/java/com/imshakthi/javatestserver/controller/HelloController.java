package com.imshakthi.javatestserver.controller;

import com.imshakthi.javatestserver.model.response.MessageBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<MessageBody> index() {

        MessageBody messageBody = MessageBody.builder().message("Welcome to HOME page!").build();

        return ResponseEntity.ok(messageBody);
    }
}
