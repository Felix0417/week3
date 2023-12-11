package org.example.web.mapper;

import org.example.model.Doctor;
import org.example.model.Hospital;
import org.example.web.dto.doctor.DoctorInputDto;
import org.example.web.dto.doctor.DoctorOutputDto;
import org.example.web.dto.hospital.HospitalOutputDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoctorMapperTest {

    DoctorMapper mapper;

    DoctorInputDto inputDto;
    Doctor doctor;
    DoctorOutputDto outputDto;
    Hospital hospital;

    @BeforeEach
    void setUp() {
        mapper = DoctorMapper.INSTANCE;

        inputDto = new DoctorInputDto("Petrov", 200, 1);
        hospital = new Hospital(1, "Hospital", "Address", LocalDateTime.now());
        doctor = new Doctor(1, "Petrov", 200, null);
        outputDto = new DoctorOutputDto(1, "Petrov", new HospitalOutputDto(1, "Hospital", "Address"));
    }

    @Test
    void mapAllFromObj() {
        List<Doctor> doctors = Collections.singletonList(doctor);
        List<DoctorOutputDto> dtoList = Collections.singletonList(outputDto);

        List<DoctorOutputDto> actual = mapper.mapAllFromObj(doctors);

        assertEquals(dtoList.get(0).getName(), actual.get(0).getName());
    }

    @Test
    void mapFromObj() {
        DoctorOutputDto actual = mapper.mapFromObj(doctor);
        actual.setHospital(new HospitalOutputDto(hospital.getId(), hospital.getName(), hospital.getAddress()));

        assertEquals(outputDto, actual);
    }

    @Test
    void mapToObj() {
        Doctor actual = mapper.mapToObj(inputDto);
        actual.setId(1);

        assertEquals(doctor, actual);
    }
}