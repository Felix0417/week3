package org.example.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Doctor;
import org.example.model.Hospital;
import org.example.service.DoctorService;
import org.example.service.HospitalService;
import org.example.web.dto.doctor.DoctorInputDto;
import org.example.web.dto.doctor.DoctorOutputDto;
import org.example.web.dto.hospital.HospitalOutputDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class DoctorControllerTest {

    @Mock
    DoctorService service;

    @Mock
    HospitalService hospitalService;

    @InjectMocks
    DoctorController controller;

    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mapper = new ObjectMapper();
    }

    @Test
    void getAll() throws JsonProcessingException {
        List<Doctor> doctors = Arrays.asList(
                new Doctor(1, "Petrov", 200, new Hospital(1, "Hospital1", "Address1", LocalDateTime.now())),
                new Doctor(2, "Petrov", 200, new Hospital(2, "Hospital2", "Address2", LocalDateTime.now()))
        );

        List<DoctorOutputDto> dtoList = Arrays.asList(
                new DoctorOutputDto(1, "Petrov", new HospitalOutputDto(1, "Hospital1", "Address1")),
                new DoctorOutputDto(2, "Petrov", new HospitalOutputDto(2, "Hospital2", "Address2"))
        );

        when(service.findAll()).thenReturn(doctors);

        ResponseEntity<List<DoctorOutputDto>> responseEntity = controller.getAll();

        String expected = mapper.writeValueAsString(dtoList);
        String actual = mapper.writeValueAsString(responseEntity.getBody());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expected, actual);
    }

    @Test
    void getById() throws JsonProcessingException {
        Doctor doctor = new Doctor(1, "Petrov", 200, new Hospital(1, "Hospital1", "Address1", LocalDateTime.now()));
        DoctorOutputDto dto = new DoctorOutputDto(1, "Petrov", new HospitalOutputDto(1, "Hospital1", "Address1"));

        when(service.findById(anyInt())).thenReturn(doctor);

        ResponseEntity<DoctorOutputDto> responseEntity = controller.getById(1);

        String expected = mapper.writeValueAsString(dto);
        String actual = mapper.writeValueAsString(responseEntity.getBody());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expected, actual);
    }

    @Test
    void save() throws JsonProcessingException {
        DoctorInputDto inputDto = new DoctorInputDto("Petrov", 200, 1);
        Doctor savedDoctor = new Doctor(1, "Petrov", 200, null);
        DoctorOutputDto dto = new DoctorOutputDto(1, "Petrov", null);

        when(service.save(any())).thenReturn(savedDoctor);

        ResponseEntity<DoctorOutputDto> responseEntity = controller.save(inputDto);

        String expected = mapper.writeValueAsString(dto);
        String actual = mapper.writeValueAsString(responseEntity.getBody());

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(expected, actual);
    }

    @Test
    void update() throws JsonProcessingException {
        DoctorInputDto inputDto = new DoctorInputDto("Petrov", 200, 1);
        Hospital hospital = new Hospital(1, "Hospital", "Address", LocalDateTime.now());
        Doctor updatedDoctor = new Doctor(1, "Petrov", 200, null);
        DoctorOutputDto dto = new DoctorOutputDto(1, "Petrov", new HospitalOutputDto(1, "Hospital", "Address"));

        when(hospitalService.findById(anyInt())).thenReturn(hospital);
        when(service.update(anyInt(), any())).thenReturn(updatedDoctor);
        updatedDoctor.setHospital(hospital);

        ResponseEntity<DoctorOutputDto> responseEntity = controller.update(1, inputDto);

        String expected = mapper.writeValueAsString(dto);
        String actual = mapper.writeValueAsString(responseEntity.getBody());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expected, actual);
    }

    @Test
    void update_bad() {
        DoctorInputDto inputDto = new DoctorInputDto("Petrov", 200, 1);
        Hospital hospital = new Hospital(1, "Hospital", "Address", LocalDateTime.now());

        when(hospitalService.findById(anyInt())).thenReturn(hospital);
        when(service.update(anyInt(), any())).thenReturn(null);

        ResponseEntity<DoctorOutputDto> responseEntity = controller.update(1, inputDto);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void delete() {
        Doctor doctor = new Doctor(1, "Petrov", 200, null);

        when(service.findById(anyInt())).thenReturn(doctor);
        when(service.delete(anyInt())).thenReturn(true);

        ResponseEntity<DoctorOutputDto> responseEntity = controller.delete(1);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void delete_not_found() {
        when(service.findById(anyInt())).thenReturn(null);

        ResponseEntity<DoctorOutputDto> responseEntity = controller.delete(1);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void delete_bad_request() {
        Doctor doctor = new Doctor(1, "Petrov", 200, null);

        when(service.findById(anyInt())).thenReturn(doctor);
        when(service.delete(anyInt())).thenReturn(false);

        ResponseEntity<DoctorOutputDto> responseEntity = controller.delete(1);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}