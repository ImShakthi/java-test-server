package com.imshakthi.javatestserver.docto.service;

import static org.junit.jupiter.api.Assertions.*;

import com.imshakthi.javatestserver.docto.model.Appointment;
import com.imshakthi.javatestserver.docto.model.TimeSlot;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppointmentServiceTest {

  private AppointmentService testService;

  @BeforeEach
  void setUp() {
    testService = new AppointmentService();
  }

  @Test
  void getAvailableTimeSlotsShouldReturnValid() {
    final var date = LocalDate.of(2025, 1, 11);
    final var zoneId = ZoneId.of("Europe/Amsterdam");

    final var appointments =
        List.of(
            Appointment.builder()
                .startTime(ZonedDateTime.of(date, LocalTime.of(10, 0), zoneId))
                .endTime(ZonedDateTime.of(date, LocalTime.of(10, 16), zoneId))
                .build(),
            Appointment.builder()
                .startTime(ZonedDateTime.of(date, LocalTime.of(13, 15), zoneId))
                .endTime(ZonedDateTime.of(date, LocalTime.of(14, 16), zoneId))
                .build(),
            Appointment.builder()
                .startTime(ZonedDateTime.of(date, LocalTime.of(15, 15), zoneId))
                .endTime(ZonedDateTime.of(date, LocalTime.of(16, 30), zoneId))
                .build(),
            Appointment.builder()
                .startTime(ZonedDateTime.of(date, LocalTime.of(12, 15), zoneId))
                .endTime(ZonedDateTime.of(date, LocalTime.of(13, 30), zoneId))
                .build(),
            Appointment.builder()
                .startTime(ZonedDateTime.of(date, LocalTime.of(11, 0), zoneId))
                .endTime(ZonedDateTime.of(date, LocalTime.of(12, 0), zoneId))
                .build());

    final var expected =
        List.of(
            TimeSlot.builder()
                .startTime(ZonedDateTime.of(date, LocalTime.of(9, 0), zoneId))
                .endTime(ZonedDateTime.of(date, LocalTime.of(9, 30), zoneId))
                .build(),
            TimeSlot.builder()
                .startTime(ZonedDateTime.of(date, LocalTime.of(9, 30), zoneId))
                .endTime(ZonedDateTime.of(date, LocalTime.of(10, 0), zoneId))
                .build(),
            TimeSlot.builder()
                .startTime(ZonedDateTime.of(date, LocalTime.of(10, 30), zoneId))
                .endTime(ZonedDateTime.of(date, LocalTime.of(11, 0), zoneId))
                .build(),
            TimeSlot.builder()
                .startTime(ZonedDateTime.of(date, LocalTime.of(14, 30), zoneId))
                .endTime(ZonedDateTime.of(date, LocalTime.of(15, 0), zoneId))
                .build(),
            TimeSlot.builder()
                .startTime(ZonedDateTime.of(date, LocalTime.of(16, 30), zoneId))
                .endTime(ZonedDateTime.of(date, LocalTime.of(17, 0), zoneId))
                .build());

    final var actual = testService.getAvailableTimeSlots(date, 1, appointments);

    assertEquals(expected, actual);
  }

  @Test
  void getAvailableTimeSlots_ShouldReturnAllPossibleTimeSlotFor1Day_WhenNoAppointmentIsBooked() {
    final List<Appointment> appointments = List.of();

    final var date = LocalDate.of(2025, 1, 11);
    final var zoneId = ZoneId.of("Europe/Amsterdam");
    final var base = ZonedDateTime.of(date, LocalTime.MIDNIGHT, zoneId);

    final var expected =
        IntStream.iterate(540, i -> i + 30)
            .limit(16)
            .mapToObj(
                hourInMinutes ->
                    TimeSlot.builder()
                        .startTime(base.plusMinutes(hourInMinutes))
                        .endTime(base.plusMinutes(hourInMinutes + 30))
                        .build())
            .toList();

    final var actual = testService.getAvailableTimeSlots(date, 1, appointments);

    assertEquals(expected, actual);
  }

  @Test
  void getAvailableTimeSlots_ShouldReturnAllPossibleTimeSlotFor7Day_WhenNoAppointmentIsBooked() {
    final List<Appointment> appointments = List.of();

    final var date = LocalDate.of(2025, 1, 11);
    final var zoneId = ZoneId.of("Europe/Amsterdam");
    final var base = ZonedDateTime.of(date, LocalTime.MIDNIGHT, zoneId);

    final var expected =
        IntStream.range(0, 7)
            .mapToObj(
                i ->
                    IntStream.iterate(540, him -> him + 30)
                        .limit(16)
                        .mapToObj(
                            hourInMinutes ->
                                TimeSlot.builder()
                                    .startTime(base.plusMinutes(hourInMinutes))
                                    .endTime(base.plusMinutes(hourInMinutes + 30))
                                    .build())
                        .toList())
            .flatMap(List::stream)
            .toList();

    final var actual = testService.getAvailableTimeSlots(date, 7, appointments);

    assertEquals(expected.toString(), actual.toString());
  }

  @Test
  void getAvailableTimeSlots_ShouldReturnEmptyTimeSlotFor7Day_WhenAllAppointmentIsBooked() {
    final var date = LocalDate.of(2025, 1, 11);
    final var zoneId = ZoneId.of("Europe/Amsterdam");
    final var base = ZonedDateTime.of(date, LocalTime.MIDNIGHT, zoneId);

    final var appointments =
        IntStream.range(0, 7)
            .mapToObj(
                dayIndex ->
                    IntStream.iterate(540, him -> him + 30)
                        .limit(16)
                        .mapToObj(
                            hourInMinutes ->
                                Appointment.builder()
                                    .startTime(base.plusDays(dayIndex).plusMinutes(hourInMinutes))
                                    .endTime(
                                        base.plusDays(dayIndex).plusMinutes(hourInMinutes + 30))
                                    .build())
                        .toList())
            .flatMap(List::stream)
            .toList();

    final var expected = List.of();

    final var actual = testService.getAvailableTimeSlots(date, 7, appointments);

    assertEquals(expected, actual);
  }
}
