package com.instantsystem.parking.wfs;

import com.instantsystem.parking.model.Location;
import com.instantsystem.parking.model.Parking;

import java.util.List;

public interface ParkingFetch {

    City getCity();

    List<Parking> fetchParkings();

    boolean isAvailableForMyLocation(Location coordinates);

}
