package com.imshakthi.javatestserver.docto.model;

import java.time.ZonedDateTime;
import lombok.Builder;

@Builder
public record Appointment(String name, ZonedDateTime startTime, ZonedDateTime endTime) {}
