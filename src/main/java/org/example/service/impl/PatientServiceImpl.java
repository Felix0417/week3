package org.example.service.impl;

import org.example.jpa.PatientRepository;
import org.example.model.Patient;
import org.example.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PreRemove;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PatientServiceImpl implements PatientService {
    private final PatientRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

    public PatientServiceImpl(PatientRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> patients = repository.findAllWithDoctors();
        logger.debug("Get All Patients");
        return patients;
    }

    @Override
    public Patient findById(int id) {
        Patient patient = repository.findByIdWithDoctors(id).orElse(null);
        if (patient != null) {
            logger.debug("Get Patient with id - {}", id);
        } else {
            logger.debug("Patient with id - {} was not found", id);
        }
        return patient;
    }

    @Override
    @Transactional
    public Patient save(Patient patient) {
        Patient newPatient = repository.save(patient);
        if (newPatient.getId() != 0) {
            logger.debug("Save new Patient with parameters - {}", newPatient);
        } else {
            logger.debug("Patient with parameters - {} was not saved", patient);
        }
        return newPatient;
    }

    @Override
    @Transactional
    public Patient update(int pos, Patient patient) {
        Patient correct = findById(pos);
        if (correct == null) {
            logger.debug("Patient with this id - {} was not found", pos);
            return null;
        }
        correct.setName(patient.getName());
        correct.setAge(patient.getAge());
        correct.setAddress(patient.getAddress());
        Patient updatePatient = repository.save(correct);
        if (updatePatient.getId() != 0) {
            logger.debug("Hospital with this id - {} was updated", pos);
        } else {
            logger.debug("Hospital with this id - {} was not updated", pos);
        }
        return updatePatient;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        Patient patient = findById(id);
        if (patient != null) {
            removeRelations(patient);
            repository.delete(patient);
        }
        if (!repository.existsById(id)) {
            logger.debug("Patient with this id - {} was competently deleted ", id);
            return true;
        } else {
            logger.debug("Patient with this id - {} was not deleted", id);
            return false;
        }
    }

    @PreRemove
    private void removeRelations(Patient patient) {
        patient.getDoctors().forEach(doctor -> doctor.getPatients().remove(patient));
    }
}
