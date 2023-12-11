package org.example.web;


import org.example.model.Hospital;
import org.example.service.HospitalService;
import org.example.web.dto.hospital.HospitalInputDto;
import org.example.web.dto.hospital.HospitalOutputDto;
import org.example.web.mapper.HospitalMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/hospitals", produces = MediaType.APPLICATION_JSON_VALUE)
public class HospitalController {

    private final HospitalService service;

    public HospitalController(HospitalService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<HospitalOutputDto>> getAll() {
        List<Hospital> hospitals = service.findAll();
        return ResponseEntity.ok(HospitalMapper.INSTANCE.mapAllFromObj(hospitals));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalOutputDto> getById(@PathVariable int id) {
        Hospital hospital = service.findById(id);
        return convert(hospital, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HospitalOutputDto> save(@RequestBody HospitalInputDto hospitalInputDto) {
        Hospital hospital = service.save(HospitalMapper.INSTANCE.mapToObj(hospitalInputDto));
        return convert(hospital, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HospitalOutputDto> update(@PathVariable int id, @RequestBody HospitalInputDto hospitalInputDto) {
        Hospital hospital = service.update(id, HospitalMapper.INSTANCE.mapToObj(hospitalInputDto));
        return convert(hospital, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HospitalOutputDto> delete(@PathVariable int id) {
        if (service.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return service.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }

    private ResponseEntity<HospitalOutputDto> convert(Hospital hospital, HttpStatus status) {
        if (hospital == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(status).body(HospitalMapper.INSTANCE.mapFromObj(hospital));
        }
    }
}
