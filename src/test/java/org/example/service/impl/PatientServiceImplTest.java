package org.example.service.impl;

import org.example.jpa.PatientRepository;
import org.example.model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class PatientServiceImplTest {

    @Mock
    PatientRepository repository;

    @InjectMocks
    PatientServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        List<Patient> patients = Arrays.asList(
                new Patient(1, "Name 1", 10, "address 1"),
                new Patient(2, "Name 2", 20, "address 2")
        );

        when(service.findAll()).thenReturn(patients);

        assertEquals(patients, service.findAll());
    }

    @Test
    void findById() {
        Patient patient = new Patient(1, "Name 1", 10, "address 1");

        when(repository.findByIdWithDoctors(1)).thenAnswer(invocationOnMock -> Optional.of(patient));

        assertEquals(patient, service.findById(1));
    }

    @Test
    void findById_bad() {
        when(repository.findByIdWithDoctors(anyInt())).thenAnswer(invocationOnMock -> Optional.empty());

        assertNull(service.findById(1));
    }

    @Test
    void save() {
        Patient patient = new Patient("Name 1", 10, "address 1");
        Patient savedPatient = new Patient(1, "Name 1", 10, "address 1");

        when(repository.save(patient)).thenReturn(savedPatient);

        assertEquals(savedPatient, service.save(patient));
    }

    @Test
    void save_bad() {
        Patient patient = new Patient("Name 1", 10, "address 1");
        when(repository.save(patient)).thenReturn(patient);

        assertNull(service.save(patient));
    }

    @Test
    void update() {
        Patient patient = new Patient(1, "N", 1, "a");
        Patient updated = new Patient(1, "Name 1", 10, "address 1");

        when(repository.findByIdWithDoctors(1)).thenAnswer(invocationOnMock -> Optional.of(patient));
        when(repository.save(patient)).thenReturn(updated);

        assertEquals(updated, service.update(1, patient));
    }

    @Test
    void update_wrong_id() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertNull(service.update(1, any()));
    }

    @Test
    void update_bad() {
        Patient patient = new Patient("Name 1", 10, "address 1");

        when(repository.findByIdWithDoctors(1)).thenAnswer(invocationOnMock -> Optional.of(patient));
        when(repository.save(patient)).thenReturn(patient);

        assertNull(service.update(1, patient));
    }

    @Test
    void delete() {
        Patient patient = new Patient(1, "Name 1", 10, "address 1");
        patient.setDoctors(new HashSet<>());

        when(repository.findByIdWithDoctors(1)).thenAnswer(invocationOnMock -> Optional.of(patient));
        when(repository.existsById(1)).thenReturn(false);

        assertTrue(service.delete(1));
    }

    @Test
    void delete_bad() {
        Patient patient = new Patient(1, "Name 1", 10, "address 1");
        patient.setDoctors(new HashSet<>());

        when(repository.findByIdWithDoctors(1)).thenAnswer(invocationOnMock -> Optional.of(patient));
        when(repository.existsById(1)).thenReturn(true);

        assertFalse(service.delete(1));
    }
}