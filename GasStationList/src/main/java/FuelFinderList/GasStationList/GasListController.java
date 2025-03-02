package FuelFinderList.GasStationList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * REST Controller for handling requests related to gas station information.
 * Provides an endpoint to retrieve a sorted list of gas stations filtered by 
 * radius, fuel type, and postal code.
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/api/messages")
public class GasListController {
    
    @Autowired
    private TankerkoenigApiService tankerkoenigApiService;

    @Autowired
    private GeolocationApiService geolocationApiService;

    @GetMapping("/stations")
    public ArrayList<GasStationListDto> getFilteredGasStations(@RequestParam int rad, @RequestParam String fuelType, @RequestParam String postalCode, @RequestParam String sortOrder) {
        GeolocationDto geoLocation = geolocationApiService.getGeolocation(postalCode);
        Double lat = geoLocation.getLat();
        Double lng = geoLocation.getLon();
        ArrayList<GasStationListDto> gasStationInformationForFrontend = tankerkoenigApiService.getGasStationsForFrontend(lat, lng, rad, fuelType, sortOrder);
                
        return gasStationInformationForFrontend;
    } 
}


