package com.travix.medusa.busyflights.controllers.rest;

import com.travix.medusa.busyflights.utils.converters.ToughJetConverter;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.repositories.ToughJetFlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class ToughJetFlightRestController {

    private final ToughJetFlightRepository toughJetFlightRepository;

    @Autowired
    public ToughJetFlightRestController(ToughJetFlightRepository toughJetFlightRepository) {
        this.toughJetFlightRepository = toughJetFlightRepository;
    }

    /**
     * Method corresponding to the url http://localhost:8080/toughjet/flights URL used to fetch
     * the ToughJet entities matching the request parameters
     *
     * @param toughJetRequest toughJetRequest
     * @return a list of entries matching the specified search criteria
     */
    @GetMapping("/toughjet/flights")
    public Collection<ToughJetResponse> findToughJetFlights(@Valid ToughJetRequest toughJetRequest) {
        return toughJetFlightRepository.findFlights(
                toughJetRequest.getFrom(),
                toughJetRequest.getTo(),
                LocalDate.parse(toughJetRequest.getOutboundDate()).atStartOfDay(ZoneOffset.UTC),
                LocalDate.parse(toughJetRequest.getInboundDate()).atStartOfDay(ZoneOffset.UTC),
                toughJetRequest.getNumberOfAdults()).
                stream().map(ToughJetConverter::fromToughJetFlightEntity)
                .collect(Collectors.toList());
    }
}
