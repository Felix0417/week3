package org.example.web.dto.doctor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoctorInputDtoTest {

    DoctorInputDto doctorInputDto = new DoctorInputDto();

    @Test
    void getName() {
        doctorInputDto = new DoctorInputDto("Name", 1, 1);

        assertEquals("Name", doctorInputDto.getName());
    }
}