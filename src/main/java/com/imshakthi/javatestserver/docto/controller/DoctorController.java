package com.imshakthi.javatestserver.docto.controller;

import com.imshakthi.javatestserver.docto.mapper.DoctorMapper;
import com.imshakthi.javatestserver.docto.model.response.DoctorResource;
import com.imshakthi.javatestserver.docto.repository.DoctorRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/docto/")
public class DoctorController {

  private final DoctorRepository doctorRepository;

  private final DoctorMapper doctorMapper;

  public DoctorController(
      final DoctorRepository doctorRepository, final DoctorMapper doctorMapper) {
    this.doctorRepository = doctorRepository;
    this.doctorMapper = doctorMapper;
  }

  @GetMapping("/doctors")
  public List<DoctorResource> getDoctors() {
    return doctorMapper.toDoctorResource(doctorRepository.findAll());
  }
}
