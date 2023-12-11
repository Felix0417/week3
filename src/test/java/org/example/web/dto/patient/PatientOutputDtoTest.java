package org.example.web.dto.patient;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatientOutputDtoTest {

    PatientOutputDto patientOutputDto = new PatientOutputDto(1, "Name", 10);
    PatientOutputDto patientOutputDto1 = new PatientOutputDto(1, "Name", 10);

    @Test
    void testEquals() {
        assertEquals(patientOutputDto, patientOutputDto1);
    }

    @Test
    void testHashCode() {
        assertEquals(patientOutputDto.hashCode(), patientOutputDto.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(patientOutputDto.toString(), patientOutputDto1.toString());
    }
}