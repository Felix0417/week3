package org.example.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Patient;
import org.example.service.PatientService;
import org.example.web.dto.patient.PatientInputDto;
import org.example.web.dto.patient.PatientOutputDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class PatientControllerTest {

    @Mock
    PatientService service;

    @InjectMocks
    PatientController controller;

    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mapper = new ObjectMapper();
    }

    @Test
    void getAll() throws JsonProcessingException {
        List<Patient> patients = Arrays.asList(
                new Patient(1, "Name", 10, "Address"),
                new Patient(2, "Name", 10, "Address")
        );
        List<PatientOutputDto> outputDto = Arrays.asList(
                new PatientOutputDto(1, "Name", 10),
                new PatientOutputDto(2, "Name", 10)
        );

        when(service.findAll()).thenReturn(patients);

        ResponseEntity<List<PatientOutputDto>> responseEntity = controller.getAll();

        String expected = mapper.writeValueAsString(outputDto);
        String actual = mapper.writeValueAsString(responseEntity.getBody());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expected, actual);
    }

    @Test
    void getById() throws JsonProcessingException {
        Patient patient = new Patient(1, "Name", 10, "Address");
        PatientOutputDto dto = new PatientOutputDto(1, "Name", 10);

        when(service.findById(anyInt())).thenReturn(patient);

        ResponseEntity<PatientOutputDto> responseEntity = controller.getById(1);
        String expected = mapper.writeValueAsString(dto);
        String actual = mapper.writeValueAsString(responseEntity.getBody());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expected, actual);
    }

    @Test
    void save() throws JsonProcessingException {
        PatientInputDto inputDto = new PatientInputDto("Name", 10, "Address");
        Patient patient = new Patient(1, "Name", 10, "Address");
        PatientOutputDto outputDto = new PatientOutputDto(1, "Name", 10);

        when(service.save(any())).thenReturn(patient);

        ResponseEntity<PatientOutputDto> responseEntity = controller.save(inputDto);

        String expected = mapper.writeValueAsString(outputDto);
        String actual = mapper.writeValueAsString(responseEntity.getBody());

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(expected, actual);
    }

    @Test
    void update() throws JsonProcessingException {
        PatientInputDto inputDto = new PatientInputDto("Name", 10, "Address");
        Patient patient = new Patient(1, "Name", 10, "Address");
        PatientOutputDto outputDto = new PatientOutputDto(1, "Name", 10);

        when(service.update(anyInt(), any())).thenReturn(patient);

        ResponseEntity<PatientOutputDto> responseEntity = controller.update(1, inputDto);

        String expected = mapper.writeValueAsString(outputDto);
        String actual = mapper.writeValueAsString(responseEntity.getBody());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expected, actual);
    }

    @Test
    void update_bad() {
        PatientInputDto inputDto = new PatientInputDto("Name", 10, "Address");

        when(service.update(anyInt(), any())).thenReturn(null);

        ResponseEntity<PatientOutputDto> responseEntity = controller.update(1, inputDto);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void delete() {
        Patient patient = new Patient(1, "Name", 10, "Address");

        when(service.findById(anyInt())).thenReturn(patient);
        when(service.delete(anyInt())).thenReturn(true);

        ResponseEntity<PatientOutputDto> responseEntity = controller.delete(1);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void delete_bad_request() {
        Patient patient = new Patient(1, "Name", 10, "Address");

        when(service.findById(anyInt())).thenReturn(patient);
        when(service.delete(anyInt())).thenReturn(false);

        ResponseEntity<PatientOutputDto> responseEntity = controller.delete(1);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void delete_not_found() {
        when(service.findById(anyInt())).thenReturn(null);

        ResponseEntity<PatientOutputDto> responseEntity = controller.delete(1);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}