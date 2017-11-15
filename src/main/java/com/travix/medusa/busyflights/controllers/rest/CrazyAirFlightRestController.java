package com.travix.medusa.busyflights.controllers.rest;

import com.travix.medusa.busyflights.utils.converters.CrazyAirConverter;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.repositories.CrazyAirFlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class CrazyAirFlightRestController {

    private final CrazyAirFlightRepository crazyAirFlightRepository;

    @Autowired
    public CrazyAirFlightRestController(CrazyAirFlightRepository crazyAirFlightRepository) {
        this.crazyAirFlightRepository = crazyAirFlightRepository;
    }

    /**
     * Method corresponding to the url http://localhost:8080/crazyair/flights URL used to fetch
     * the CrazyAir entities matching the request parameters
     *
     * @param crazyAirRequest crazyAirRequest
     * @return a list of entries matching the specified search criteria
     */
    @GetMapping("/crazyair/flights")
    public Collection<CrazyAirResponse> findCrazyAirFlights(@Valid CrazyAirRequest crazyAirRequest) {
        return crazyAirFlightRepository.findFlights(
                crazyAirRequest.getOrigin(),
                crazyAirRequest.getDestination(),
                LocalDate.parse(crazyAirRequest.getDepartureDate()).atStartOfDay(ZoneOffset.UTC),
                LocalDate.parse(crazyAirRequest.getReturnDate()).atStartOfDay(ZoneOffset.UTC),
                crazyAirRequest.getPassengerCount()).
                stream().map(CrazyAirConverter::fromCrazyAirEntityToCrazyAirResponse)
                .collect(Collectors.toList());
    }
}