package com.instantsystem.parking.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Parking {
    private String name;
    private Location location;
    private Integer distance;
    private Integer capacity;
    private Integer availableSpots;
}
