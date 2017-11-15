package com.travix.medusa.busyflights.utils.loaders;

import com.travix.medusa.busyflights.entities.CrazyAirFlight;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;

public class CrazyAirFlightBuilder {


    public static CrazyAirFlight generateCrazyAirFlight() {
        return CrazyAirFlight.builder()
                .airline("WIZZ Air")
                .price(new BigDecimal(10))
                .cabinClass("C")
                .departureAirportCode("ABC")
                .destinationAirportCode("DEF")
                .departureDate(LocalDate.parse("2007-12-03").atStartOfDay(ZoneOffset.UTC))
                .arrivalDate(LocalDate.parse("2007-12-04").atStartOfDay(ZoneOffset.UTC))
                .numberOfAvailableSeats(25)
                .build();
    }

    static CrazyAirFlight generateCrazyAirFlight(String airline, BigDecimal price, String cabinClass,
                                                 String departureAirportName, String destinationAirportCode,
                                                 String departureDate, String arrivalDate,
                                                 Integer numberOfAvailableSeats) {
        return CrazyAirFlight.builder()
                .airline(airline)
                .price(price)
                .cabinClass(cabinClass)
                .departureAirportCode(departureAirportName)
                .destinationAirportCode(destinationAirportCode)
                .departureDate(LocalDate.parse(departureDate).atStartOfDay(ZoneOffset.UTC))
                .arrivalDate(LocalDate.parse(arrivalDate).atStartOfDay(ZoneOffset.UTC))
                .numberOfAvailableSeats(numberOfAvailableSeats)
                .build();
    }
}
