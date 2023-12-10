package org.example.web.mapper;


import org.example.model.Hospital;
import org.example.web.dto.hospital.HospitalInputDto;
import org.example.web.dto.hospital.HospitalOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface HospitalMapper extends EntityMapper<Hospital, HospitalInputDto, HospitalOutputDto> {
    HospitalMapper INSTANCE = Mappers.getMapper(HospitalMapper.class);

    @Override
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "address", target = "address")
    List<HospitalOutputDto> mapAllFromObj(List<Hospital> hospitals);

    @Override
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "address", target = "address")
    HospitalOutputDto mapFromObj(Hospital hospital);

    @Override
    @Mapping(source = "name", target = "name")
    @Mapping(source = "address", target = "address")
    Hospital mapToObj(HospitalInputDto dto);
}
