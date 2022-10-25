package com.instantsystem.parking.service;

import com.instantsystem.parking.exception.ParkingServiceException;
import com.instantsystem.parking.mapper.LocationMapper;
import com.instantsystem.parking.mapper.ParkingMapper;
import com.instantsystem.parking.model.Location;
import com.instantsystem.parking.rest.dto.ParkingDTO;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class ParkingServiceImpl implements ParkingService {

    private final LocationMapper locationMapper;
    private final ParkingMapper parkingMapper;

    public List<ParkingDTO> getParkingNearLocation(@NonNull String locationText, Integer radius) throws ParkingServiceException {
        Location location = locationMapper.fromString(locationText);

        Stream<ParkingDTO> parkingDTOStream = parkingFetcher.getParkingsNearLocation(location).stream();

        if (radius != null) {
            parkingDTOStream = parkingDTOStream.filter(parking -> parking.getDistance() <= radius);
        }
        return parkingDTOStream.collect(Collectors.toList());
    }
}
