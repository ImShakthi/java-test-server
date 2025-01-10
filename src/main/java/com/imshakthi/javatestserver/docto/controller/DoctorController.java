package com.imshakthi.javatestserver.docto.controller;

import com.imshakthi.javatestserver.docto.mapper.DoctorMapper;
import com.imshakthi.javatestserver.docto.model.response.DoctorResource;
import com.imshakthi.javatestserver.docto.service.DoctorService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/docto")
public class DoctorController {

  private final DoctorService doctorService;

  private final DoctorMapper doctorMapper;

  public DoctorController(final DoctorService doctorService, final DoctorMapper doctorMapper) {
    this.doctorService = doctorService;
    this.doctorMapper = doctorMapper;
  }

  @GetMapping("/doctors")
  public List<DoctorResource> getDoctors() {
    return doctorMapper.toDoctorResource(doctorService.getDoctors());
  }
}
