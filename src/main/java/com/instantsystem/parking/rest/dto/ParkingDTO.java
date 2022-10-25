package com.instantsystem.parking.rest.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ParkingDTO {
    private String name;
    private LocationDTO location;
    private Integer distance;
    private Integer capacity;
    private Integer availableSpots;
}
