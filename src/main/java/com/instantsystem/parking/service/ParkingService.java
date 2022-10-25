package com.instantsystem.parking.service;

import com.instantsystem.parking.exception.ParkingServiceException;
import com.instantsystem.parking.rest.dto.ParkingDTO;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParkingService {
    List<ParkingDTO> getParkingNearLocation(@NonNull String location, Integer radius) throws ParkingServiceException;
}
