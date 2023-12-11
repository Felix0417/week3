package org.example.web.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.example.model.Hospital;
import org.example.web.dto.hospital.HospitalInputDto;
import org.example.web.dto.hospital.HospitalOutputDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-11T23:30:14+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 11.0.21 (Ubuntu)"
)
public class HospitalMapperImpl implements HospitalMapper {

    @Override
    public List<HospitalOutputDto> mapAllFromObj(List<Hospital> hospitals) {
        if ( hospitals == null ) {
            return null;
        }

        List<HospitalOutputDto> list = new ArrayList<HospitalOutputDto>( hospitals.size() );
        for ( Hospital hospital : hospitals ) {
            list.add( mapFromObj( hospital ) );
        }

        return list;
    }

    @Override
    public HospitalOutputDto mapFromObj(Hospital hospital) {
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

    @Override
    public Hospital mapToObj(HospitalInputDto dto) {
        if ( dto == null ) {
            return null;
        }

        Hospital hospital = new Hospital();

        hospital.setName( dto.getName() );
        hospital.setAddress( dto.getAddress() );

        return hospital;
    }
}
