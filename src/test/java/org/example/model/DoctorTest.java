package org.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoctorTest {

    Doctor doctor = new Doctor(1, "Petrov", 200, null);

    @Test
    void testHashCode() {
        assertEquals(doctor.hashCode(), doctor.hashCode());
    }
}