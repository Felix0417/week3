package org.example.web.dto.hospital;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HospitalInputDtoTest {

    HospitalInputDto hospitalInputDto = new HospitalInputDto();

    @Test
    void setName() {
        hospitalInputDto.setName("Name");

        assertEquals("Name", hospitalInputDto.getName());
    }

    @Test
    void setAddress() {
        hospitalInputDto.setAddress("Address");

        assertEquals("Address", hospitalInputDto.getAddress());
    }
}