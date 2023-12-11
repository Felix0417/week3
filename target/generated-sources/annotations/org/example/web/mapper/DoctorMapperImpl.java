package org.example.web.mapper;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.example.model.Doctor;
import org.example.model.Hospital;
import org.example.model.Patient;
import org.example.web.dto.doctor.DoctorInputDto;
import org.example.web.dto.doctor.DoctorOutputDto;
import org.example.web.dto.hospital.HospitalOutputDto;
import org.example.web.dto.patient.PatientOutputDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-12T01:50:08+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 11.0.21 (Ubuntu)"
)
public class DoctorMapperImpl implements DoctorMapper {

    @Override
    public List<DoctorOutputDto> mapAllFromObj(List<Doctor> doctors) {
        if ( doctors == null ) {
            return null;
        }

        List<DoctorOutputDto> list = new ArrayList<DoctorOutputDto>( doctors.size() );
        for ( Doctor doctor : doctors ) {
            list.add( mapFromObj( doctor ) );
        }

        return list;
    }

    @Override
    public DoctorOutputDto mapFromObj(Doctor doctor) {
        if ( doctor == null ) {
            return null;
        }

        int id = 0;
        String name = null;
        HospitalOutputDto hospital = null;

        id = doctor.getId();
        name = doctor.getName();
        hospital = hospitalToHospitalOutputDto( doctor.getHospital() );

        DoctorOutputDto doctorOutputDto = new DoctorOutputDto( id, name, hospital );

        doctorOutputDto.setPatients( patientSetToPatientOutputDtoSet( doctor.getPatients() ) );

        return doctorOutputDto;
    }

    @Override
    public Doctor mapToObj(DoctorInputDto inputDto) {
        if ( inputDto == null ) {
            return null;
        }

        Doctor doctor = new Doctor();

        doctor.setName( inputDto.getName() );
        doctor.setSalary( inputDto.getSalary() );

        return doctor;
    }

    protected HospitalOutputDto hospitalToHospitalOutputDto(Hospital hospital) {
        if ( hospital == null ) {
            return null;
        }

        int id = 0;
        String name = null;
        String address = null;

        id = hospital.getId();
        name = hospital.getName();
        address = hospital.getAddress();

        HospitalOutputDto hospitalOutputDto = new HospitalOutputDto( id, name, address );

        return hospitalOutputDto;
    }

    protected Set<DoctorOutputDto> doctorSetToDoctorOutputDtoSet(Set<Doctor> set) {
        if ( set == null ) {
            return null;
        }

        Set<DoctorOutputDto> set1 = new LinkedHashSet<DoctorOutputDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Doctor doctor : set ) {
            set1.add( mapFromObj( doctor ) );
        }

        return set1;
    }

    protected PatientOutputDto patientToPatientOutputDto(Patient patient) {
        if ( patient == null ) {
            return null;
        }

        long id = 0L;
        String name = null;
        int age = 0;

        id = patient.getId();
        name = patient.getName();
        age = patient.getAge();

        PatientOutputDto patientOutputDto = new PatientOutputDto( id, name, age );

        patientOutputDto.setDoctors( doctorSetToDoctorOutputDtoSet( patient.getDoctors() ) );

        return patientOutputDto;
    }

    protected Set<PatientOutputDto> patientSetToPatientOutputDtoSet(Set<Patient> set) {
        if ( set == null ) {
            return null;
        }

        Set<PatientOutputDto> set1 = new LinkedHashSet<PatientOutputDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Patient patient : set ) {
            set1.add( patientToPatientOutputDto( patient ) );
        }

        return set1;
    }
}
