package com.imshakthi.javatestserver.controller;

import com.imshakthi.javatestserver.model.request.LoginRequest;
import com.imshakthi.javatestserver.model.response.LoginResponse;
import com.imshakthi.javatestserver.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {
  private final JwtService jwtService;

  public AuthController(final JwtService jwtService) {
    this.jwtService = jwtService;
  }

  @PostMapping("/login")
  ResponseEntity<LoginResponse> generateToken(final @RequestBody LoginRequest loginRequest) {
    return ResponseEntity.ok(new LoginResponse(jwtService.generateToken(loginRequest.username())));
  }
}
