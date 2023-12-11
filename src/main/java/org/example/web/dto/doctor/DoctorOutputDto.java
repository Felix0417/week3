package org.example.web.dto.doctor;


import org.example.web.dto.hospital.HospitalOutputDto;
import org.example.web.dto.patient.PatientOutputDto;

import java.util.Objects;
import java.util.Set;

public class DoctorOutputDto {

    private final int id;

    private final String name;

    //ManyToOne
    private HospitalOutputDto hospital;

    //ManyToMany
    private Set<PatientOutputDto> patients;

    public DoctorOutputDto(int id, String name, HospitalOutputDto hospital) {
        this.id = id;
        this.name = name;
        this.hospital = hospital;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public HospitalOutputDto getHospital() {
        return hospital;
    }

    public void setHospital(HospitalOutputDto hospital) {
        this.hospital = hospital;
    }

    public Set<PatientOutputDto> getPatients() {
        return patients;
    }

    public void setPatients(Set<PatientOutputDto> patients) {
        this.patients = patients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorOutputDto that = (DoctorOutputDto) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(hospital, that.hospital) && Objects.equals(patients, that.patients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, hospital, patients);
    }

    @Override
    public String toString() {
        return "DoctorOutputDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hospital=" + hospital +
                ", patients=" + patients +
                '}';
    }
}
