package com.imshakthi.javatestserver.docto.service;

import com.imshakthi.javatestserver.docto.mapper.DoctorMapper;
import com.imshakthi.javatestserver.docto.model.Doctor;
import com.imshakthi.javatestserver.docto.repository.DoctorRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DoctorService {
  private final DoctorRepository doctorRepository;
  private final DoctorMapper doctorMapper;

  public DoctorService(final DoctorRepository doctorRepository, final DoctorMapper doctorMapper) {
    this.doctorRepository = doctorRepository;
    this.doctorMapper = doctorMapper;
  }

  public List<Doctor> getDoctors() {
    final var doctors = doctorRepository.findAll();
    return doctorMapper.toDoctor(doctors);
  }
}
