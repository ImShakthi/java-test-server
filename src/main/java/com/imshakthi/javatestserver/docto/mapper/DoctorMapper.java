package com.imshakthi.javatestserver.docto.mapper;

import com.imshakthi.javatestserver.docto.entity.Doctor;
import com.imshakthi.javatestserver.docto.model.response.DoctorResource;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
  @Mapping(target = "startTime", source = "startTime", dateFormat = "HH:mm")
  @Mapping(target = "endTime", source = "endTime", dateFormat = "HH:mm")
  List<DoctorResource> toDoctorResource(
      final List<com.imshakthi.javatestserver.docto.model.Doctor> doctor);

  List<com.imshakthi.javatestserver.docto.model.Doctor> toDoctor(final List<Doctor> doctor);
}
