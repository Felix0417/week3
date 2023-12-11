package org.example.web.dto.hospital;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HospitalOutputDtoTest {

    HospitalOutputDto outputDto = new HospitalOutputDto(1, "Name", "Address");
    HospitalOutputDto outputDto1 = new HospitalOutputDto(1, "Name", "Address");

    @Test
    void testEquals() {
        assertEquals(outputDto, outputDto1);
    }

    @Test
    void testHashCode() {
        assertEquals(outputDto.hashCode(), outputDto1.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(outputDto.toString(), outputDto1.toString());
    }
}