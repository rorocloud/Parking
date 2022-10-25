package com.instantsystem.parking.mapper;

import com.instantsystem.parking.model.Parking;
import com.instantsystem.parking.rest.dto.ParkingDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { LocationMapper.class })
public interface ParkingMapper {
    ParkingDTO mapperToDTO(Parking parking);
}
