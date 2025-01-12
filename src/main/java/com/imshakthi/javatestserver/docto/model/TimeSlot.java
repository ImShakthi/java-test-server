package com.imshakthi.javatestserver.docto.model;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Builder;

@Builder
public record TimeSlot(ZonedDateTime startTime, ZonedDateTime endTime) {

  @Override
  public String toString() {
    return String.format(
        "[%s-%s]",
        startTime.format(DateTimeFormatter.ofPattern("HH:mm")),
        endTime.format(DateTimeFormatter.ofPattern("HH:mm")));
  }
}
