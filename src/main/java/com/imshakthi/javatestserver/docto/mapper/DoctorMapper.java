package com.imshakthi.javatestserver.docto.mapper;

import com.imshakthi.javatestserver.docto.entity.Doctor;
import com.imshakthi.javatestserver.docto.model.response.DoctorResource;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
  List<DoctorResource> toDoctorResource(
      final List<com.imshakthi.javatestserver.docto.model.Doctor> doctor);

  List<com.imshakthi.javatestserver.docto.model.Doctor> toDoctor(final List<Doctor> doctor);
}
