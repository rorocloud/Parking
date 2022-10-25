package com.instantsystem.parking.service;

import com.instantsystem.parking.exception.ParkingServiceException;
import com.instantsystem.parking.mapper.LocationMapper;
import com.instantsystem.parking.mapper.ParkingMapper;
import com.instantsystem.parking.model.Location;
import com.instantsystem.parking.model.Parking;
import com.instantsystem.parking.rest.dto.ParkingDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParkingServiceImplTests {

    @InjectMocks
    ParkingServiceImpl parkingService;

    @Mock
    LocationMapper locationMapper;

    @Mock
    ParkingMapper parkingMapper;

    @Mock
    ParkingFetcher parkingFetcher;

    @Test
    void getParkingNearLocation() throws ParkingServiceException {
        double latitude = 43.54;
        double longitude = 6.95;
        Location location = new Location(latitude, longitude);
        when(locationMapper.fromString(latitude + "," + longitude)).thenReturn(location);

        Parking parking1 = Parking.builder().name("P1").build();
        Parking parking2 = Parking.builder().name("P2").build();
        when(parkingFetcher.getParkingsForMyLocationWithDistance(location)).thenReturn(List.of(parking1, parking2));

        ParkingDTO parking1DTO = ParkingDTO.builder().name("P1").build();
        ParkingDTO parking2DTO = ParkingDTO.builder().name("P2").build();

        when(parkingMapper.mapperToDTO(parking1)).thenReturn(parking1DTO);
        when(parkingMapper.mapperToDTO(parking2)).thenReturn(parking2DTO);

        // When
        List<ParkingDTO> result = parkingService.getParkingNearLocation(latitude + "," + longitude, null);

        // Then
        verify(parkingFetcher).getParkingsForMyLocationWithDistance(location);
        verify(parkingMapper).mapperToDTO(parking1);
        verify(parkingMapper).mapperToDTO(parking2);

        assertThat(result).containsExactly(parking1DTO, parking2DTO);
    }
}
