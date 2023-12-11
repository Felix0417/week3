package org.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatientTest {

    Patient patient = new Patient(1, "Name", 10, "Address");

    @Test
    void testHashCode() {
        assertEquals(patient.hashCode(), patient.hashCode());
    }
}