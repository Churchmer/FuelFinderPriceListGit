package FuelFinderList.GasStationList.DataObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for mapping Tankerkoenig API responses.
 */
@Getter
@Setter
@NoArgsConstructor
public class TankerkoenigApiResponseDto {
    private Station stations[];

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Station {
        private String country;
        private String id;
        private String name;
        private String brand;
        private String street;
        private String postalCode;
        private String place;
        private Coordinates coords;
        private boolean isOpen;
        private String closesAt;
        private OpeningTime openingTimes[];
        private double dist;
        private Fuel fuels[];
        private int volatility;
        
        @Getter
        @Setter
        @NoArgsConstructor
        public static class Coordinates {
            private double lat;
            private double lng;
        }

        @Getter
        @Setter
        @NoArgsConstructor
        public static class OpeningTime {
            private String days[];
            private TimeRange times[];

            @Getter
            @Setter
            public static class TimeRange {
                private String open;
                private String close;
            }
        }

        @Getter
        @Setter
        @NoArgsConstructor
        public static class Fuel {
            private String category;
            private String name;
            private double price;
            private LastChange lastChange;

            @Getter
            @Setter
            @NoArgsConstructor
            public static class LastChange {
                private double amount;
                private String timestamp;
            }
        }

    }
}
