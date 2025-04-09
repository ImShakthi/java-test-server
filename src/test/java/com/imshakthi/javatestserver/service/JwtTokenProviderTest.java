package com.imshakthi.javatestserver.service;

import static org.junit.jupiter.api.Assertions.*;

import io.jsonwebtoken.MalformedJwtException;
import java.time.Instant;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JwtTokenProviderTest {
  private static final String TOKEN =
      "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJuYW1lIiwiaWF0IjoxNzQ0MjAyMzU3LCJleHAiOjE3NDU5NDY1NjF9.W05YqNxl69Odf2lH9IXGSVKehMNrVOygD-SB0s8APRsyPhrIhjw_a9vj6_kWUEQ0";
  private JwtTokenProvider jwtTokenProvider;

  @BeforeEach
  void setUp() {
    jwtTokenProvider =
        new JwtTokenProvider(
            "5367566859703373367639792F423F452848284D6251655468576D5A71347437",
            Instant.now().plusSeconds(1800).getEpochSecond());
  }

  @Test
  void generateToken() {

    final var actual = jwtTokenProvider.generateToken("name", new Date(1744202357750L));

    assertNotNull(actual);
    assertEquals(TOKEN, actual);
  }

  @Test
  void getUsername() {

    final var actual = jwtTokenProvider.getUsername(TOKEN);

    assertNotNull(actual);
    assertEquals("name", actual);
  }

  @Test
  void validateToken() {
    assertTrue(jwtTokenProvider.validateToken(TOKEN));

    assertThrowsExactly(
        MalformedJwtException.class, () -> jwtTokenProvider.validateToken("invalid_token"));
  }
}
