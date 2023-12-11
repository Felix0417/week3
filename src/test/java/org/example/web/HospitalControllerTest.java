package org.example.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.model.Hospital;
import org.example.service.HospitalService;
import org.example.web.dto.hospital.HospitalInputDto;
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

class HospitalControllerTest {
    @Mock
    HospitalService service;

    @InjectMocks
    HospitalController controller;

    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    void getAll() throws Exception {
        List<Hospital> hospitals = Arrays.asList(
                new Hospital(1, "Name", "Address", LocalDateTime.now()),
                new Hospital(2, "Name", "Address", LocalDateTime.now())
        );
        List<HospitalOutputDto> hospitalOutputDto = Arrays.asList(
                new HospitalOutputDto(1, "Name", "Address"),
                new HospitalOutputDto(2, "Name", "Address")
        );

        when(service.findAll()).thenReturn(hospitals);

        ResponseEntity<List<HospitalOutputDto>> responseEntity = controller.getAll();
        String expected = mapper.writeValueAsString(hospitalOutputDto);
        String actual = mapper.writeValueAsString(responseEntity.getBody());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expected, actual);
    }

    @Test
    void getById() throws Exception {
        Hospital hospital = new Hospital(1, "Name", "Address", LocalDateTime.now());
        HospitalOutputDto dto = new HospitalOutputDto(1, "Name", "Address");

        when(service.findById(1)).thenReturn(hospital);

        ResponseEntity<HospitalOutputDto> responseEntity = controller.getById(1);

        String expected = mapper.writeValueAsString(dto);
        String actual = mapper.writeValueAsString(responseEntity.getBody());


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expected, actual);
    }

    @Test
    void save() throws Exception {
        HospitalInputDto inputDto = new HospitalInputDto("Name", "Address");
        Hospital savedHospital = new Hospital(1, "Name", "Address", LocalDateTime.now());
        HospitalOutputDto outputDto = new HospitalOutputDto(1, "Name", "Address");

        when(service.save(any())).thenReturn(savedHospital);

        ResponseEntity<HospitalOutputDto> responseEntity = controller.save(inputDto);

        String expected = mapper.writeValueAsString(outputDto);
        String actual = mapper.writeValueAsString(responseEntity.getBody());

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(expected, actual);
    }

    @Test
    void update() throws JsonProcessingException {
        HospitalInputDto inputDto = new HospitalInputDto("Name", "Address");
        Hospital updatedHospital = new Hospital(1, "Name", "Address", LocalDateTime.now());
        HospitalOutputDto outputDto = new HospitalOutputDto(1, "Name", "Address");

        when(service.update(anyInt(), any())).thenReturn(updatedHospital);

        ResponseEntity<HospitalOutputDto> responseEntity = controller.update(1, inputDto);

        String expected = mapper.writeValueAsString(outputDto);
        String actual = mapper.writeValueAsString(responseEntity.getBody());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expected, actual);
    }

    @Test
    void update_bad() {
        HospitalInputDto inputDto = new HospitalInputDto("Name", "Address");

        when(service.update(anyInt(), any())).thenReturn(null);

        ResponseEntity<HospitalOutputDto> responseEntity = controller.update(1, inputDto);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void delete() {
        Hospital hospital = new Hospital(1, "Name", "Address", LocalDateTime.now());

        when(service.findById(1)).thenReturn(hospital);
        when(service.delete(1)).thenReturn(true);

        ResponseEntity<HospitalOutputDto> responseEntity = controller.delete(1);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void delete_not_found() {
        when(service.findById(1)).thenReturn(null);

        ResponseEntity<HospitalOutputDto> responseEntity = controller.delete(1);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void delete_bad_request() {
        Hospital hospital = new Hospital(1, "Name", "Address", LocalDateTime.now());

        when(service.findById(1)).thenReturn(hospital);
        when(service.delete(1)).thenReturn(false);

        ResponseEntity<HospitalOutputDto> responseEntity = controller.delete(1);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}