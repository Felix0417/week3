package org.example.web.dto.patient;


import org.example.web.dto.doctor.DoctorOutputDto;

import java.util.Objects;
import java.util.Set;

public class PatientOutputDto {

    private final long id;

    private final String name;

    private final int age;

    //ManyToMany
    private Set<DoctorOutputDto> doctors;

    public PatientOutputDto(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Set<DoctorOutputDto> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<DoctorOutputDto> doctors) {
        this.doctors = doctors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientOutputDto outputDto = (PatientOutputDto) o;
        return id == outputDto.id && age == outputDto.age && Objects.equals(name, outputDto.name) && Objects.equals(doctors, outputDto.doctors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, doctors);
    }

    @Override
    public String toString() {
        return "PatientOutputDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", doctors=" + doctors +
                '}';
    }
}
