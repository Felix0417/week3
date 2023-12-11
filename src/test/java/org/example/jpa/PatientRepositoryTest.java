package org.example.jpa;

import org.example.PostgreSQLContainersTest;
import org.example.model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class PatientRepositoryTest extends PostgreSQLContainersTest {

    @Mock
    PatientRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllWithDoctors() {
        List<Patient> patients = Arrays.asList(
                new Patient(1, "Name 1", 10, "address 1"),
                new Patient(2, "Name 2", 20, "address 2")
        );

        when(repository.findAllWithDoctors()).thenReturn(patients);

        List<Patient> actual = repository.findAllWithDoctors();

        assertEquals(patients, actual);
    }

    @Test
    void findByIdWithDoctors() {
        Patient patient = new Patient(1, "Name 1", 10, "address 1");

        when(repository.findByIdWithDoctors(anyInt())).thenReturn(Optional.of(patient));

        Patient actual = repository.findByIdWithDoctors(1).orElse(null);

        assertEquals(patient, actual);
    }
}