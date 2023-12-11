package org.example.web;

import org.example.model.Patient;
import org.example.service.PatientService;
import org.example.web.dto.patient.PatientInputDto;
import org.example.web.dto.patient.PatientOutputDto;
import org.example.web.mapper.PatientMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/patients", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PatientOutputDto>> getAll() {
        List<Patient> patients = service.findAll();
        patients.forEach(p -> p.setDoctors(null));
        return ResponseEntity.ok(PatientMapper.INSTANCE.mapAllFromObj(patients));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientOutputDto> getById(@PathVariable int id) {
        Patient patient = service.findById(id);
        return convert(patient, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PatientOutputDto> save(@RequestBody PatientInputDto patientInputDto) {
        Patient patient = service.save(PatientMapper.INSTANCE.mapToObj(patientInputDto));
        return convert(patient, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientOutputDto> update(@PathVariable int id, @RequestBody PatientInputDto patientInputDto) {
        Patient patient = service.update(id, PatientMapper.INSTANCE.mapToObj(patientInputDto));
        return convert(patient, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PatientOutputDto> delete(@PathVariable int id) {
        if (service.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return service.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }

    private ResponseEntity<PatientOutputDto> convert(Patient patient, HttpStatus status) {
        if (patient == null) {
            return ResponseEntity.notFound().build();
        } else if (patient.getDoctors() != null) {
            patient.getDoctors().forEach(d -> d.setPatients(null));
        }
        return ResponseEntity.status(status).body(PatientMapper.INSTANCE.mapFromObj(patient));
    }
}
