package com.instantsystem.parking.mapper;

import com.instantsystem.parking.model.Location;
import com.instantsystem.parking.rest.dto.LocationDTO;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

@Component
public class LocationMapper {
    private static final Set<String> SEPARATORS = Set.of(
            ",",
            " "
    );

    @Named("LocationParser")
    public Location fromString(String location) {
        if (StringUtils.isNotBlank(location)) {
            String[] coordinates = location.split(
                    SEPARATORS.stream()
                            .filter(location::contains)
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException(String.format("Format of '%s' is not valid", location))));
            return new Location(Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]));
        }
        return null;
    }

    public LocationDTO mapperToDTO(Location location) {
        return new LocationDTO(location.getLatitude(), location.getLongitude());
    }
}
