package com.travix.medusa.busyflights.utils.converters;

import com.travix.medusa.busyflights.utils.farecalculators.FareCalculator;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.entities.ToughJetFlight;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ToughJetConverter {

    public static ToughJetResponse fromToughJetFlightEntity(ToughJetFlight toughJetFlight) {
        return ToughJetResponse.builder()
                .carrier(toughJetFlight.getCarrier())
                .basePrice(toughJetFlight.getBasePrice().doubleValue())
                .tax(toughJetFlight.getTax().doubleValue())
                .discount(toughJetFlight.getDiscount().doubleValue())
                .departureAirportName(toughJetFlight.getDepartureAirportName())
                .arrivalAirportName(toughJetFlight.getArrivalAirportName())
                .outboundDateTime(toughJetFlight.getOutboundDateTime().format(DateTimeFormatter.ISO_INSTANT))
                .inboundDateTime(toughJetFlight.getInboundDateTime().format(DateTimeFormatter.ISO_INSTANT))
                .build();
    }

    public static BusyFlightsResponse fromToughJetResponseToBusyFlightsResponse(ToughJetResponse toughJetResponse, Integer numberOfPassengers) {
        return BusyFlightsResponse.builder()
                .airline(toughJetResponse.getCarrier())
                .supplier("ToughJet")
                .fare(FareCalculator.computeToughJetFinalPrice(toughJetResponse, numberOfPassengers))
                .departureAirportCode(toughJetResponse.getDepartureAirportName())
                .destinationAirportCode(toughJetResponse.getArrivalAirportName())
                .departureDate(ZonedDateTime.parse(toughJetResponse.getOutboundDateTime()).toLocalDateTime().format(DateTimeFormatter.ISO_DATE_TIME))
                .arrivalDate(ZonedDateTime.parse(toughJetResponse.getInboundDateTime()).toLocalDateTime().format(DateTimeFormatter.ISO_DATE_TIME))
                .build();
    }


}
