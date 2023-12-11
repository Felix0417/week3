package org.example.web.dto.patient;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatientInputDtoTest {

    PatientInputDto patientInputDto = new PatientInputDto();

    @Test
    void setName() {
        patientInputDto.setName("Name");

        assertEquals("Name", patientInputDto.getName());
    }

    @Test
    void setAge() {
        patientInputDto.setAge(1);

        assertEquals(1, patientInputDto.getAge());
    }

    @Test
    void setAddress() {
        patientInputDto.setAddress("Address");

        assertEquals("Address", patientInputDto.getAddress());
    }
}