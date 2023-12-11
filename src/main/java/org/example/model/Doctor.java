package org.example.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int salary;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "doctor_patient",
            joinColumns = {@JoinColumn(name = "patients_id")},
            inverseJoinColumns = {@JoinColumn(name = "doctors_id")}
    )
    private Set<Patient> patients;

    public Doctor() {
    }

    public Doctor(int id, String name, int salary, Hospital hospital) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.hospital = hospital;
    }

    public Doctor(String name, int salary, Hospital hospital) {
        this.name = name;
        this.salary = salary;
        this.hospital = hospital;
        this.patients = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return id == doctor.id &&
                Objects.equals(name, doctor.name) &&
                Objects.equals(salary, doctor.salary) &&
                Objects.equals(hospital, doctor.hospital);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, salary, hospital);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", hospital=" + hospital +
                '}';
    }
}
