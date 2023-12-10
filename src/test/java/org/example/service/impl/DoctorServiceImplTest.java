package org.example.service.impl;

import org.example.jpa.DoctorRepository;
import org.example.model.Doctor;
import org.example.model.Hospital;
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

class DoctorServiceImplTest {

    @InjectMocks
    DoctorServiceImpl service;

    @Mock
    DoctorRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        List<Doctor> doctors = Arrays.asList(
                new Doctor(1, "Smith", 50000, new Hospital()),
                new Doctor(2, "Brown", 60000, new Hospital())
        );

        when(repository.findAllWithPatients()).thenReturn(doctors);

        assertEquals(doctors, service.findAll());
    }

    @Test
    void findById() {
        Doctor doctor = new Doctor(1, "Smith", 50000, new Hospital());

        when(repository.findByIdWithPatients(1)).thenAnswer(invocationOnMock -> Optional.of(doctor));

        assertEquals(doctor, service.findById(1));
    }

    @Test
    void findById_return_null() {
        when(repository.findByIdWithPatients(anyInt())).thenAnswer(invocationOnMock -> Optional.empty());

        assertNull(service.findById(1));
    }

    @Test
    void save() {
        Doctor doctor = new Doctor("Smith", 50000, new Hospital());
        Doctor savedDoctor = new Doctor(1, "Smith", 50000, new Hospital());

        when(repository.save(doctor)).thenReturn(savedDoctor);

        assertEquals(savedDoctor, service.save(doctor));
    }

    @Test
    void save_bad() {
        Doctor doctor = new Doctor("Smith", 50000, new Hospital());
        doctor.setPatients(new HashSet<>());

        when(repository.save(any())).thenReturn(doctor);

        assertNull(service.save(doctor));
    }

    @Test
    void update() {
        Doctor doctor = new Doctor(1, "S", 5, new Hospital());
        Doctor updatedDoctor = new Doctor(1, "Smith", 50000, new Hospital());

        when(repository.findByIdWithPatients(1)).thenReturn(Optional.of(doctor));
        when(repository.save(doctor)).thenReturn(updatedDoctor);

        assertEquals(updatedDoctor, service.update(1, doctor));
    }

    @Test
    void update_wrong_id() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertNull(service.update(1, any()));
    }

    @Test
    void update_bad_safe() {
        Doctor doctor = new Doctor(0, "S", 5, new Hospital());

        when(repository.findByIdWithPatients(1)).thenReturn(Optional.of(doctor));
        when(repository.save(doctor)).thenReturn(doctor);

        assertNull(service.update(1, doctor));
    }

    @Test
    void delete() {
        Doctor doctor = new Doctor(1, "S", 5, new Hospital());
        doctor.setPatients(new HashSet<>());

        when(repository.findByIdWithPatients(1)).thenReturn(Optional.of(doctor));
        when(repository.existsById(1)).thenReturn(false);

        assertTrue(service.delete(1));
    }

    @Test
    void delete_bad() {
        Doctor doctor = new Doctor(1, "S", 5, new Hospital());
        doctor.setPatients(new HashSet<>());

        when(repository.findByIdWithPatients(1)).thenReturn(Optional.of(doctor));
        when(repository.existsById(1)).thenReturn(true);

        assertFalse(service.delete(1));
    }
}