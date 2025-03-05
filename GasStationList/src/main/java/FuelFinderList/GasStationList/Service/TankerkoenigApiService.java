package FuelFinderList.GasStationList.Service;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import FuelFinderList.GasStationList.DataObjects.GasStationListDto;
import FuelFinderList.GasStationList.DataObjects.TankerkoenigApiResponseDto;
import FuelFinderList.GasStationList.DataObjects.TankerkoenigApiResponseDto.Station;
import FuelFinderList.GasStationList.DataObjects.TankerkoenigApiResponseDto.Station.Fuel;


@Service
public class TankerkoenigApiService {

    @Value("${tankerkoenig.api.key}")
    private String apiKey; 

    private final RestTemplate restTemplate = new RestTemplate();
 
    /**
     * Fetches gas stations within a specified radius using the Tankerkönig API.
     *
     * @param lat Latitude of the search center.
     * @param lng Longitude of the search center.
     * @param rad Search radius in kilometers.
     * @return Array of gas station data.
     * @throws RuntimeException if API response is null.
     */
    public Station[] getGasStations(double lat, double lng, int rad) {
        String url = "https://creativecommons.tankerkoenig.de/api/v4/stations/search?"
                + "apikey=" + apiKey 
                + "&lat=" + lat 
                + "&lng=" + lng 
                + "&rad=" + rad;

        TankerkoenigApiResponseDto response = restTemplate.getForObject(url, TankerkoenigApiResponseDto.class);
        if (response != null) {
            return response.getStations();
        } else {
            throw new RuntimeException("Unable to fetch data from Tankerkönig API");
        }
    }

    /**
     * Retrieves and sorts gas stations based on location, fuel type, and sort order.
     *
     * @param lat Latitude of the search center.
     * @param lng Longitude of the search center.
     * @param rad Search radius in kilometers.
     * @param fuelType Type of fuel to filter stations by.
     * @param sortOrder Sort order ("Price Asc" or by distance).
     * @return List of gas stations formatted for frontend display.
     */
    public ArrayList<GasStationListDto> getGasStationsForFrontend(double lat, double lng, int rad, String fuelType, String sortOrder) {
        ArrayList<GasStationListDto> gasStationInformationForFrontend = new ArrayList<GasStationListDto>();

        Station[] allGasStationInformation = this.getGasStations(lat, lng, rad);

        for(Station station : allGasStationInformation) {
            for(Fuel fuel : station.getFuels()) {
                if(fuel.getName().equals(fuelType)) {
                    gasStationInformationForFrontend.add(new GasStationListDto(station.getBrand(), station.getStreet(), station.getPostalCode(), station.getPlace(), station.getDist(), fuel.getName(), fuel.getPrice()));
                }
            }
        }

        if(sortOrder.equals("Price Asc")) {
            Collections.sort(gasStationInformationForFrontend, new java.util.Comparator<GasStationListDto>() {
                @Override
                public int compare(GasStationListDto o1, GasStationListDto o2) {
                    // Ersetze das Komma durch einen Punkt
                    double price1 = Double.parseDouble(o1.getFuelPrice().replace(',', '.'));
                    double price2 = Double.parseDouble(o2.getFuelPrice().replace(',', '.'));
                    return Double.compare(price1, price2);
                }
            });
        } else {
            Collections.sort(gasStationInformationForFrontend, new java.util.Comparator<GasStationListDto>() {
                @Override
                public int compare(GasStationListDto o1, GasStationListDto o2) {
                    double distance1 = Double.parseDouble(o1.getDistance().replace(',', '.'));
                    double distance2 = Double.parseDouble(o2.getDistance().replace(',', '.'));
                    return Double.compare(distance1, distance2);
                }
            });
        }
        

        return gasStationInformationForFrontend;

    }
}
