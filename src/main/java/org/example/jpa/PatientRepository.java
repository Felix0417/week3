package org.example.jpa;

import org.example.model.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface PatientRepository extends CrudRepository<Patient, Integer> {

    @Query("SELECT DISTINCT p FROM Patient p")
    List<Patient> findAllWithDoctors();

    @Query("SELECT DISTINCT p FROM Patient p LEFT JOIN FETCH p.doctors d WHERE p.id=:id")
    Optional<Patient> findByIdWithDoctors(@Param("id") int id);
}
