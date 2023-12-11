package org.example.jpa;


import org.example.model.Doctor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@Transactional(readOnly = true)
public interface DoctorRepository extends CrudRepository<Doctor, Integer> {

    @Query("SELECT DISTINCT d FROM Doctor d")
    List<Doctor> findAllWithPatients();

    @Query("SELECT DISTINCT d FROM Doctor d JOIN FETCH d.hospital LEFT JOIN FETCH d.patients p WHERE d.id=:id")
    Optional<Doctor> findByIdWithPatients(@Param("id") int id);

    @Query("SELECT DISTINCT d FROM Doctor d WHERE d.hospital.id=:id")
    Set<Doctor> findAllByHospitalId(@Param("id") int id);
}
