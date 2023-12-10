package org.example.web.mapper;

import org.example.model.Patient;
import org.example.web.dto.patient.PatientInputDto;
import org.example.web.dto.patient.PatientOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PatientMapper extends EntityMapper<Patient, PatientInputDto, PatientOutputDto> {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "age", target = "age")
    @Mapping(target = "doctors", ignore = true)
    List<PatientOutputDto> mapAllFromObj(List<Patient> patients);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "age", target = "age")
    @Mapping(source = "doctors", target = "doctors")
    PatientOutputDto mapFromObj(Patient patient);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "age", target = "age")
    @Mapping(source = "address", target = "address")
    Patient mapToObj(PatientInputDto dto);
}
