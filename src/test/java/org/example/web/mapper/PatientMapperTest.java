package org.example.web.mapper;

import org.example.model.Patient;
import org.example.web.dto.patient.PatientInputDto;
import org.example.web.dto.patient.PatientOutputDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatientMapperTest {

    PatientMapper mapper;

    PatientInputDto inputDto;

    Patient patient;

    PatientOutputDto outputDto;

    @BeforeEach
    void setUp() {
        mapper = PatientMapper.INSTANCE;

        inputDto = new PatientInputDto("Name", 10, "Address");
        patient = new Patient(1, "Name", 10, "Address");
        outputDto = new PatientOutputDto(1, "Name", 10);
    }

    @Test
    void mapAllFromObj() {
        List<Patient> patients = Collections.singletonList(patient);
        List<PatientOutputDto> patientOutputDtoList = Collections.singletonList(outputDto);

        List<PatientOutputDto> actual = mapper.mapAllFromObj(patients);

        assertEquals(patientOutputDtoList, actual);
    }

    @Test
    void mapFromObj() {
        PatientOutputDto actual = mapper.mapFromObj(patient);

        assertEquals(outputDto, actual);
    }

    @Test
    void mapToObj() {
        Patient actual = mapper.mapToObj(inputDto);
        actual.setId(1);

        assertEquals(patient, actual);
    }
}