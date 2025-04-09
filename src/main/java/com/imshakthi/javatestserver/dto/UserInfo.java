package com.imshakthi.javatestserver.dto;

import lombok.Builder;

@Builder
public record UserInfo(String name, String email, String password, String roles) {}
