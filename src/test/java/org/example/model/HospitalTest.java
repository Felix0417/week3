package org.example.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HospitalTest {
    LocalDateTime est = LocalDateTime.now();
    Hospital hospital = new Hospital(1, "Name", "Address", est);

    @Test
    void getEstimated() {
        assertEquals(est, hospital.getEstimated());
    }

    @Test
    void testHashCode() {
        assertEquals(hospital.hashCode(), hospital.hashCode());
    }
}