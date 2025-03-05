package FuelFinderList.GasStationList.DataObjects;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for representing just the latitude and the longitude of a geolocation.
 */
@Setter
@Getter
public class GeolocationDto {
    private Double lat;
    private Double lon;

    public GeolocationDto(Double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
    }
}
