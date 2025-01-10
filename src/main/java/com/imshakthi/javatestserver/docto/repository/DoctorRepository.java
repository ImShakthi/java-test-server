package com.imshakthi.javatestserver.docto.repository;

import com.imshakthi.javatestserver.docto.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DoctorRepository extends JpaRepository<Doctor, Long> {}
