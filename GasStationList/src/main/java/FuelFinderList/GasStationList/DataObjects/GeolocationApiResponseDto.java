package FuelFinderList.GasStationList.DataObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for mapping Nominatim API responses.
 */
@Getter
@Setter
@NoArgsConstructor
public class GeolocationApiResponseDto {
    private long place_id;
    private String license;
    private String osm_type;
    private String lat;
    private String lon;
    private String classType;
    private String type;
    private int place_rank;
    private float importance;
    private String adresstype;
    private String name;
    private String display_name;
    private String[] boundingbox;
}
