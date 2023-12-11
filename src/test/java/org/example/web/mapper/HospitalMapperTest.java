package org.example.web.mapper;

import org.example.model.Hospital;
import org.example.web.dto.hospital.HospitalInputDto;
import org.example.web.dto.hospital.HospitalOutputDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HospitalMapperTest {

    HospitalMapper mapper;

    HospitalInputDto inputDto;

    Hospital hospital;

    HospitalOutputDto outputDto;

    @BeforeEach
    void setUp() {
        mapper = HospitalMapper.INSTANCE;

        inputDto = new HospitalInputDto("Name", "Address");
        hospital = new Hospital(1, "Name", "Address", LocalDateTime.now());
        outputDto = new HospitalOutputDto(1, "Name", "Address");
    }

    @Test
    void mapAllFromObj() {
        List<Hospital> hospitals = Collections.singletonList(hospital);
        List<HospitalOutputDto> hospitalOutputDto = Collections.singletonList(outputDto);

        List<HospitalOutputDto> actual = mapper.mapAllFromObj(hospitals);

        assertEquals(hospitalOutputDto, actual);
    }

    @Test
    void mapFromObj() {
        HospitalOutputDto actual = mapper.mapFromObj(hospital);

        assertEquals(outputDto, actual);
    }

    @Test
    void mapToObj() {
        Hospital actual = mapper.mapToObj(inputDto);
        actual.setId(1);
        hospital.setEstimated(null);

        assertEquals(hospital, actual);
    }
}