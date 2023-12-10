package org.example.web;

import org.example.model.Doctor;
import org.example.model.Hospital;
import org.example.service.DoctorService;
import org.example.service.HospitalService;
import org.example.web.dto.doctor.DoctorInputDto;
import org.example.web.dto.doctor.DoctorOutputDto;
import org.example.web.mapper.DoctorMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/doctors", produces = MediaType.APPLICATION_JSON_VALUE)
public class DoctorController {

    private final DoctorService service;

    private final HospitalService hospitalService;

    public DoctorController(DoctorService service, HospitalService hospitalService) {
        this.service = service;
        this.hospitalService = hospitalService;
    }

    @GetMapping
    public ResponseEntity<List<DoctorOutputDto>> getAll() {
        List<Doctor> doctors = service.findAll();
        doctors.forEach(d -> d.setPatients(null));
        return ResponseEntity.ok(DoctorMapper.INSTANCE.mapAllFromObj(doctors));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorOutputDto> getById(@PathVariable int id) {
        Doctor doctor = service.findById(id);
        return convert(doctor);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<DoctorOutputDto> save(@RequestBody DoctorInputDto doctorInputDto) {
        Doctor doctor = service.save(DoctorMapper.INSTANCE.mapToObj(doctorInputDto));
        return convert(doctor);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<DoctorOutputDto> update(@PathVariable int id, @RequestBody DoctorInputDto doctorInputDto) {
        Hospital hospital = hospitalService.findById(doctorInputDto.getHospitalId());
        Doctor doctor = DoctorMapper.INSTANCE.mapToObj(doctorInputDto);
        doctor.setHospital(hospital);
        Doctor updatedDoctor = service.update(id, doctor);
        return convert(updatedDoctor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<DoctorOutputDto> delete(@PathVariable int id) {
        if (service.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<DoctorOutputDto> convert(Doctor doctor) {
        if (doctor == null) {
            return ResponseEntity.notFound().build();
        } else if (doctor.getPatients() != null) {
            doctor.getPatients().forEach(p -> p.setDoctors(null));
        }
        return ResponseEntity.ok(DoctorMapper.INSTANCE.mapFromObj(doctor));
    }
}