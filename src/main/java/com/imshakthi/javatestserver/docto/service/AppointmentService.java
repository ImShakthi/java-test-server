package com.imshakthi.javatestserver.docto.service;

import com.imshakthi.javatestserver.docto.model.Appointment;
import com.imshakthi.javatestserver.docto.model.TimeSlot;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.IntStream;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

  static final int START_HOUR_IN_MINUTES = 540;
  static final int MAX_POSSIBLE_SLOTS = 16;
  static final int TIME_SLOT_SIZE = 30;

  static final ZoneId ZONE_ID = ZoneId.of("Europe/Amsterdam");

  public List<TimeSlot> getAvailableTimeSlots(
      final LocalDate startDate, final int noOfDays, final List<Appointment> appointments) {

    return IntStream.range(0, noOfDays)
        .mapToObj(
            dayIndex ->
                getAvailableTimeSlotForGivenDate(startDate.plusDays(dayIndex), appointments))
        .flatMap(List::stream)
        .toList();
  }

  private List<TimeSlot> getAvailableTimeSlotForGivenDate(
      final LocalDate dateToCheck, final List<Appointment> appointments) {

    final var baseAppointment = ZonedDateTime.of(dateToCheck, LocalTime.MIDNIGHT, ZONE_ID);
    final var filteredSameDayAppointments =
        appointments.stream()
            .filter(appointment -> isSameDay(appointment.startTime(), baseAppointment))
            .toList();

    return IntStream.iterate(START_HOUR_IN_MINUTES, hourIndex -> hourIndex + TIME_SLOT_SIZE)
        .limit(MAX_POSSIBLE_SLOTS)
        .mapToObj(hoursInMinutes -> generateTimeSlot(baseAppointment, hoursInMinutes))
        .filter(timeSlot -> isTimeSlotAvailable(filteredSameDayAppointments, timeSlot))
        .toList();
  }

  private boolean isTimeSlotAvailable(
      final List<Appointment> appointments, final TimeSlot timeSlot) {

    return appointments.stream()
        .noneMatch(appointment -> isTimeSlotColliding(appointment, timeSlot));
  }

  private boolean isSameDay(final ZonedDateTime t1, final ZonedDateTime t2) {
    return t1.toLocalDate().equals(t2.toLocalDate());
  }

  private boolean isTimeSlotColliding(final Appointment appointment, final TimeSlot timeSlot) {
    return timeSlot.startTime().isBefore(appointment.endTime())
        && timeSlot.endTime().isAfter(appointment.startTime());
  }

  private TimeSlot generateTimeSlot(final ZonedDateTime baseAppointment, final int hoursInMinutes) {
    return TimeSlot.builder()
        .startTime(baseAppointment.plusMinutes(hoursInMinutes))
        .endTime(baseAppointment.plusMinutes(hoursInMinutes + TIME_SLOT_SIZE))
        .build();
  }
}
