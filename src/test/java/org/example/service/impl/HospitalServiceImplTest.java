package org.example.service.impl;

import org.example.jpa.DoctorRepository;
import org.example.jpa.HospitalRepository;
import org.example.model.Doctor;
import org.example.model.Hospital;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.Mass;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class HospitalServiceImplTest {

    @Mock
    HospitalRepository repository;

    @Mock
    DoctorRepository doctorRepository;

    @InjectMocks
    HospitalServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        List<Hospital> hospitals = Arrays.asList(
                new Hospital(1, "Hospital 1", "address 1", LocalDateTime.now()),
                new Hospital(2, "Hospital 2", "address 2", LocalDateTime.now())
        );

        when(service.findAll()).thenReturn(hospitals);

        assertEquals(hospitals, service.findAll());
    }

    @Test
    void findById() {
        Hospital hospital = new Hospital(1, "Hospital 1", "address 1", LocalDateTime.now());

        when(service.findById(1)).thenAnswer(invocation -> Optional.of(hospital));

        assertEquals(hospital, service.findById(1));
    }

    @Test
    void findById_bad(){
        when(service.findById(2)).thenAnswer(invocation -> Optional.empty());

        assertNull(service.findById(2));
    }

    @Test
    void save() {
        Hospital hospital = new Hospital("Hospital 1", "address 1");
        Hospital savedHospital = new Hospital(1, "Hospital 1", "address 1", LocalDateTime.now());

        when(repository.save(hospital)).thenReturn(savedHospital);

        assertEquals(savedHospital, service.save(hospital));
    }

    @Test
    void save_bad(){
        Hospital hospital = new Hospital(0, "Hospital 1", "address 1", LocalDateTime.now());

        when(repository.save(hospital)).thenReturn(hospital);

        assertNull(service.save(hospital));
    }

    @Test
    void update() {
        Hospital hospital =  new Hospital("Hospital 1", "address 1");
        Hospital updatedHospital = new Hospital(1, "H", "a", LocalDateTime.now());

        when(repository.findById(1)).thenReturn(Optional.of(hospital));
        when(repository.save(hospital)).thenReturn(updatedHospital);
        when(service.update(1, hospital)).thenReturn(updatedHospital);

        assertEquals(updatedHospital, service.update(1, hospital));
    }

    @Test
    void update_wrong_id(){
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertNull(service.update(1, any()));
    }

    @Test
    void update_bad(){
        Hospital hospital = new Hospital(0, "H", "a", LocalDateTime.now());

        when(repository.findById(1)).thenReturn(Optional.of(hospital));
        when(repository.save(hospital)).thenReturn(hospital);

        assertNull(service.update(1, hospital));
    }

    @Test
    void delete() {
        when(repository.existsById(1)).thenReturn(true);

        assertTrue(service.delete(1));
    }

    @Test
    void delete_bad(){
        when(repository.existsById(1)).thenReturn(false);

        assertFalse(service.delete(1));
    }

}