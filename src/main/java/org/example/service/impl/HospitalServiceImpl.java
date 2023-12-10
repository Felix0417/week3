package org.example.service.impl;


import org.example.jpa.DoctorRepository;
import org.example.jpa.HospitalRepository;
import org.example.model.Doctor;
import org.example.model.Hospital;
import org.example.service.HospitalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PreRemove;
import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository repository;

    private final DoctorRepository doctorRepository;

    private static final Logger logger = LoggerFactory.getLogger(HospitalServiceImpl.class);

    public HospitalServiceImpl(HospitalRepository repository, DoctorRepository doctorRepository) {
        this.repository = repository;
        this.doctorRepository = doctorRepository;
    }

    public List<Hospital> findAll() {
        List<Hospital> hospitals = (List<Hospital>) repository.findAll();
        logger.debug("Get All Hospitals");
        return hospitals;
    }

    @Override
    public Hospital findById(int id) {
        Hospital hospital = repository.findById(id).orElse(null);
        if (hospital != null) {
            logger.debug("Get Hospital with id - {}", id);
        } else {
            logger.debug("Hospital with id - {} was not found", id);
        }
        return hospital;
    }

    @Override
    @Transactional
    public Hospital save(Hospital hospital) {
        Hospital newHospital = repository.save(hospital);
        if (hospital.getId() != 0) {
            logger.debug("Save new Hospital with parameters - {}", newHospital);
        } else {
            logger.debug("Hospital with parameters - {} was not saved", newHospital);
        }
        return newHospital;
    }

    @Override
    @Transactional
    public Hospital update(int pos, Hospital hospital) {
        if (findById(pos) == null) {
            logger.debug("Hospital with this id - {} was not found", pos);
            return null;
        }
        hospital.setId(pos);
        Hospital updateHospital = repository.save(hospital);
        if (updateHospital.getId() != 0) {
            logger.debug("Hospital with this id - {} was updated", pos);
        } else {
            logger.debug("Hospital with this id - {} was not updated", pos);
        }
        return updateHospital;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        Set<Doctor> doctors = doctorRepository.findAllByHospital(id);
        removeRelations(doctors);
        repository.deleteById(id);
        if (repository.existsById(id)) {
            logger.debug("Hospital with this id - {} was competently deleted ", id);
            return true;
        } else {
            logger.debug("Hospital with this id - {} was not deleted", id);
            return false;
        }
    }

    @PreRemove
    private void removeRelations(Set<Doctor> doctors) {
        doctors.forEach(doctor -> doctor.setHospital(null));
    }

}
