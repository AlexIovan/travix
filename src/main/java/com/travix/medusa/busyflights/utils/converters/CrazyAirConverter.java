package com.travix.medusa.busyflights.utils.converters;

import com.travix.medusa.busyflights.utils.farecalculators.FareCalculator;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.entities.CrazyAirFlight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CrazyAirConverter {

    public static CrazyAirResponse fromCrazyAirEntityToCrazyAirResponse(CrazyAirFlight crazyAirFlight) {
        return CrazyAirResponse.builder()
                .airline(crazyAirFlight.getAirline())
                .price(crazyAirFlight.getPrice().doubleValue())
                .departureAirportCode(crazyAirFlight.getDepartureAirportCode())
                .destinationAirportCode(crazyAirFlight.getDestinationAirportCode())
                .cabinClass(crazyAirFlight.getCabinClass())
                .departureDate(crazyAirFlight.getDepartureDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .arrivalDate(crazyAirFlight.getArrivalDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build();
    }

    public static BusyFlightsResponse fromCrazyAirResponseToBusyFlightsResponse(CrazyAirResponse crazyAirResponse, Integer numberOfPassengers) {
        return BusyFlightsResponse.builder()
                .airline(crazyAirResponse.getAirline())
                .supplier("CrazyAir")
                .fare(FareCalculator.computeCrazyAirFinalPrice(crazyAirResponse.getPrice(), numberOfPassengers))
                .departureAirportCode(crazyAirResponse.getDepartureAirportCode())
                .destinationAirportCode(crazyAirResponse.getDestinationAirportCode())
                .departureDate(LocalDateTime.parse(crazyAirResponse.getDepartureDate()).format(DateTimeFormatter.ISO_DATE_TIME))
                .arrivalDate(LocalDateTime.parse(crazyAirResponse.getArrivalDate()).format(DateTimeFormatter.ISO_DATE_TIME))
                .build();
    }


}
