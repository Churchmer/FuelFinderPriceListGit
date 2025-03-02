package FuelFinderList.GasStationList;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeolocationApiService {

    private final RestTemplate restTemplate = new RestTemplate();
    
    /**
     * Fetches geolocation data from Nominatim API for the specified postal code in Germany.
     *
     * @param postalCode The postal code to search for geolocation data.
     * @return An array of GeolocationApiResponseDto objects.
     * @throws RuntimeException if no data is returned from the API.
     */
    public GeolocationApiResponseDto[] getGeolocationApiResponse(String postalCode) {
        String url = "https://nominatim.openstreetmap.org/search?format=json"
                        + "&postalcode=" + postalCode
                        + "&country=Germany";

        GeolocationApiResponseDto[] response = restTemplate.getForObject(url, GeolocationApiResponseDto[].class);
        if (response.length > 0) {
            return response;
        } else {
            throw new RuntimeException("Unable to fetch data from nominatim API. Maybe the postal code is not available");
        }
    }

    /**
     * Retrieves the geolocation for a given postal code and truncates 
     * coordinates to two decimal places, and returns as a GeolocationDto.
     *
     * @param postalCode The postal code for which geolocation is determined.
     * @return GeolocationDto containing the latitude and longitude.
     */
    public GeolocationDto getGeolocation(String postalCode) {
        GeolocationDto geoLocationDto = new GeolocationDto(null, null);
        GeolocationApiResponseDto[] geolocationApiResponse = getGeolocationApiResponse(postalCode);

        for(GeolocationApiResponseDto geoLocation : geolocationApiResponse) {
            String latString = geoLocation.getLat();
            String lonString = geoLocation.getLon();

            // Parse Strings to Double
            double latDouble = Double.parseDouble(latString);
            double lonDouble = Double.parseDouble(lonString);

            // Limit the precision to two decimal places
            BigDecimal latDecimal = new BigDecimal(latDouble).setScale(2, RoundingMode.HALF_UP);
            BigDecimal lonDecimal = new BigDecimal(lonDouble).setScale(2, RoundingMode.HALF_UP);

            geoLocationDto.setLat(latDecimal.doubleValue());
            geoLocationDto.setLon(lonDecimal.doubleValue());
    
        }

        return geoLocationDto;
    }
}
