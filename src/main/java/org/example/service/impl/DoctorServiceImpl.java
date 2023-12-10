package org.example.service.impl;

import org.example.jpa.DoctorRepository;
import org.example.model.Doctor;
import org.example.service.DoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);

    public DoctorServiceImpl(DoctorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Doctor> findAll() {
        List<Doctor> doctors = repository.findAllWithPatients();
        logger.debug("Get All Doctors");
        return doctors;
    }

    @Override
    public Doctor findById(int id) {
        Doctor doctor = repository.findByIdWithPatients(id).orElse(null);
        if (doctor != null) {
            logger.debug("Get Doctor with id - {}", id);
        } else {
            logger.debug("Doctor with id - {} was not found", id);
        }
        return doctor;
    }

    @Override
    @Transactional
    public Doctor save(Doctor doctor) {
        doctor.setPatients(new HashSet<>());
        Doctor newDoctor = repository.save(doctor);
        if (newDoctor.getId() != 0) {
            logger.debug("Save new Doctor with parameters - {}", newDoctor);
        } else {
            logger.debug("Doctor was not saved with this parameters - {}", doctor);
        }
        return newDoctor;
    }

    @Override
    @Transactional
    public Doctor update(int pos, Doctor doctor) {
        Doctor corrected = findById(pos);
        if (findById(pos) == null) {
            logger.debug("Doctor with id - {} was not found", pos);
            return null;
        }
        corrected.setName(doctor.getName());
        corrected.setSalary(doctor.getSalary());
        corrected.setHospital(doctor.getHospital());

        Doctor updatedDoctor = repository.save(corrected);
        if (updatedDoctor.getId() != 0) {
            logger.debug("Doctor with id - {} was updated", pos);
        } else {
            logger.debug("Doctor with id - {} was not updated", pos);
        }
        return updatedDoctor;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        Doctor doctor = findById(id);
        if (doctor != null) {
            doctor.getPatients().clear();
            repository.delete(doctor);
        }
        if (!repository.existsById(id)) {
            logger.debug("Doctor with this id - {} was competently deleted ", id);
            return true;
        } else {
            logger.debug("Doctor with this id - {} was not deleted", id);
            return false;
        }
    }
}
