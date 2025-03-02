package FuelFinderList.GasStationList;

import java.text.DecimalFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for representing information about a gas station in a fuel finder application.
 */
@Getter
@Setter
public class GasStationListDto {
    DecimalFormat formatDistance = new DecimalFormat("#0.0");
    DecimalFormat formatPrice = new DecimalFormat("#0.00");

    private String brand;
    private String street; 
    private String postalCode;
    private String place;
    private String distance;
    private String fuelType;
    private String fuelPrice;

    public GasStationListDto(String brand, String street, String postalCode, String place, double distance, String fuelType, double fuelPrice) {
        this.brand = brand;
        this.street = street;
        this.postalCode = postalCode;
        this.place = place;
        this.distance = formatDistance.format(distance);
        this.fuelType = fuelType;
        this.fuelPrice = formatPrice.format(fuelPrice);
    }
}
