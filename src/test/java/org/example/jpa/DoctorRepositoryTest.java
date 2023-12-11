package org.example.jpa;

import org.example.PostgreSQLContainersTest;
import org.example.model.Doctor;
import org.example.model.Hospital;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class DoctorRepositoryTest extends PostgreSQLContainersTest {

    @Mock
    DoctorRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllWithPatients() {
        List<Doctor> doctors = Arrays.asList(
                new Doctor(1, "Smith", 50000, new Hospital()),
                new Doctor(2, "Brown", 60000, new Hospital())
        );

        when(repository.findAllWithPatients()).thenReturn(doctors);

        List<Doctor> actual = repository.findAllWithPatients();

        assertEquals(doctors, actual);
    }

    @Test
    void findByIdWithPatients() {
        Doctor doctor = new Doctor(1, "Smith", 50000, new Hospital());

        when(repository.findByIdWithPatients(anyInt())).thenReturn(Optional.of(doctor));

        Doctor actual = repository.findByIdWithPatients(1).orElse(null);

        assertEquals(doctor, actual);
    }

    @Test
    void findAllByHospitalId() {
        Hospital hospital = new Hospital(1, "Hospital", "Address", LocalDateTime.now());
        Set<Doctor> doctors = Set.of(
                new Doctor(1, "Smith", 50000, hospital),
                new Doctor(2, "Brown", 60000, hospital)
        );

        when(repository.findAllByHospitalId(anyInt())).thenReturn(doctors);

        Set<Doctor> actual = repository.findAllByHospitalId(1);

        assertEquals(doctors, actual);
    }
}