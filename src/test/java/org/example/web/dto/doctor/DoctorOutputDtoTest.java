package org.example.web.dto.doctor;

import org.example.web.dto.hospital.HospitalOutputDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoctorOutputDtoTest {

    DoctorOutputDto dto = new DoctorOutputDto(1, "Petrov", new HospitalOutputDto(1, "Hospital1", "Address1"));
    DoctorOutputDto dto1 = new DoctorOutputDto(1, "Petrov", new HospitalOutputDto(1, "Hospital1", "Address1"));

    @Test
    void testEquals() {
        assertEquals(dto, dto1);
    }

    @Test
    void testHashCode() {
        assertEquals(dto.hashCode(), dto1.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(dto.toString(), dto1.toString());
    }
}