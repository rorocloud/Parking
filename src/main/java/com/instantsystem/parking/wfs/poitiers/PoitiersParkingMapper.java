package com.instantsystem.parking.wfs.poitiers;

import com.instantsystem.parking.mapper.LocationMapper;
import com.instantsystem.parking.model.Parking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = LocationMapper.class)
public interface PoitiersParkingMapper {
    @Mapping(target = "name", source = "fields.nom")
    @Mapping(target = "location", source = "fields.geo_point_2d", qualifiedByName = "LocationParser")
    @Mapping(target = "capacity", source = "fields.capacite")
    @Mapping(target = "availableSpots", source = "fields.places_restantes")
    Parking mapperToCommonModel(ParkingXmlElement parkingXmlElement);
}
