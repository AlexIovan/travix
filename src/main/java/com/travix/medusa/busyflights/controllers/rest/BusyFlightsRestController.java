package com.travix.medusa.busyflights.controllers.rest;

import com.travix.medusa.busyflights.utils.converters.CrazyAirConverter;
import com.travix.medusa.busyflights.utils.converters.ToughJetConverter;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.utils.urls.SupplierURL;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class BusyFlightsRestController {


    /**
     * Method corresponding to the url http://localhost:8080/busyflights URL used to aggregate and
     * sort ascending according to price the flights found from ToughJet and CrazyAir services
     *
     * @param busyFlightsRequest busyFlightsRequest
     * @return a sorted list of entries matching the specified search criteria
     */
    @GetMapping("/busyflights")
    public Collection<BusyFlightsResponse> findToughJetFlights(@Valid BusyFlightsRequest busyFlightsRequest) {
        CrazyAirResponse[] crazyAirFlights = searchCrazyAirFlights(busyFlightsRequest);
        ToughJetResponse[] toughJetFlights = searchToughJetFlights(busyFlightsRequest);

        return aggregateAndOrderFlights(crazyAirFlights, toughJetFlights, busyFlightsRequest.getNumberOfPassengers());
    }

    private List<BusyFlightsResponse> aggregateAndOrderFlights(CrazyAirResponse[] crazyAirFlights, ToughJetResponse[] toughJetFlights, Integer numberOfPassengers) {
        return Stream.concat(
                // aggregate the results from both sources
                Stream.of(toughJetFlights).map(toughJetResponse -> ToughJetConverter.fromToughJetResponseToBusyFlightsResponse(toughJetResponse, numberOfPassengers)),
                Stream.of(crazyAirFlights).map(crazyAirResponse -> CrazyAirConverter.fromCrazyAirResponseToBusyFlightsResponse(crazyAirResponse, numberOfPassengers))).
                // sort ascending by price
                sorted(Comparator.comparing(BusyFlightsResponse::getFare)).
                collect(Collectors.toList());
    }

    private CrazyAirResponse[] searchCrazyAirFlights(@Valid BusyFlightsRequest busyFlightsRequest) {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(SupplierURL.CRAZY_AIR.getUrl())
                .queryParam("origin", busyFlightsRequest.getOrigin())
                .queryParam("destination", busyFlightsRequest.getDestination())
                .queryParam("departureDate", busyFlightsRequest.getDepartureDate())
                .queryParam("returnDate", busyFlightsRequest.getReturnDate())
                .queryParam("passengerCount", busyFlightsRequest.getNumberOfPassengers());

        return restTemplate.getForEntity(builder.build().encode().toUri(), CrazyAirResponse[].class).getBody();
    }

    private ToughJetResponse[] searchToughJetFlights(@Valid BusyFlightsRequest busyFlightsRequest) {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(SupplierURL.TOUGH_JET.getUrl())
                .queryParam("from", busyFlightsRequest.getOrigin())
                .queryParam("to", busyFlightsRequest.getDestination())
                .queryParam("outboundDate", busyFlightsRequest.getDepartureDate())
                .queryParam("inboundDate", busyFlightsRequest.getReturnDate())
                .queryParam("numberOfAdults", busyFlightsRequest.getNumberOfPassengers());

        return restTemplate.getForEntity(builder.build().encode().toUri(), ToughJetResponse[].class).getBody();
    }
}
