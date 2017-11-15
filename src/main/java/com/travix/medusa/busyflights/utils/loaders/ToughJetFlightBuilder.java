package com.travix.medusa.busyflights.utils.loaders;

import com.travix.medusa.busyflights.entities.ToughJetFlight;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class ToughJetFlightBuilder {


    public static ToughJetFlight generateToughJetFlight() {
        return ToughJetFlight.builder()
                .carrier("Lufthansa")
                .basePrice(BigDecimal.TEN)
                .tax(BigDecimal.ONE)
                .discount(BigDecimal.ONE)
                .departureAirportName("ABC")
                .arrivalAirportName("DEF")
                .outboundDateTime(ZonedDateTime.parse("2007-12-03T00:00:00+00:00"))
                .inboundDateTime(ZonedDateTime.parse("2007-12-04T00:00:00+00:00"))
                .numberOfAvailableSeats(25)
                .build();
    }

    static ToughJetFlight generateToughJetFlight(String carrier, BigDecimal basePrice, BigDecimal tax,
                                                 BigDecimal discount, String departureAirportName,
                                                 String arrivalAirportName, String outboundDateTime,
                                                 String inboundDateTime, Integer numberOfAvailableSeats) {
        return ToughJetFlight.builder()
                .carrier(carrier)
                .basePrice(basePrice)
                .tax(tax)
                .discount(discount)
                .departureAirportName(departureAirportName)
                .arrivalAirportName(arrivalAirportName)
                .outboundDateTime(ZonedDateTime.parse(outboundDateTime))
                .inboundDateTime(ZonedDateTime.parse(inboundDateTime))
                .numberOfAvailableSeats(numberOfAvailableSeats)
                .build();
    }
}
