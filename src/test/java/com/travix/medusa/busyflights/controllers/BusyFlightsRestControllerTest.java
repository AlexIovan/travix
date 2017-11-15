package com.travix.medusa.busyflights.controllers;

import com.travix.medusa.busyflights.controllers.rest.BusyFlightsRestController;
import com.travix.medusa.busyflights.utils.loaders.CrazyAirFlightBuilder;
import com.travix.medusa.busyflights.utils.loaders.ToughJetFlightBuilder;
import com.travix.medusa.busyflights.repositories.CrazyAirFlightRepository;
import com.travix.medusa.busyflights.repositories.ToughJetFlightRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZonedDateTime;
import java.util.Collections;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BusyFlightsRestController.class, secure = false)
public class BusyFlightsRestControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToughJetFlightRepository toughJetFlightRepository;

    @MockBean
    private CrazyAirFlightRepository crazyAirFlightRepository;

    @Test
    public void testRetrieveToughJetFlights() throws Exception {
        Mockito.when(
                toughJetFlightRepository.findFlights("ABC", "DEF", ZonedDateTime.parse("2007-12-03T00:00:00+00:00"),
                        ZonedDateTime.parse("2007-12-04T00:00:00+00:00"), 1)).thenReturn(Collections.singletonList(ToughJetFlightBuilder.generateToughJetFlight()));
        Mockito.when(
                crazyAirFlightRepository.findFlights("ABC", "DEF", ZonedDateTime.parse("2007-12-03T00:00:00+00:00"),
                        ZonedDateTime.parse("2007-12-04T00:00:00+00:00"), 1)).thenReturn(Collections.singletonList(CrazyAirFlightBuilder.generateCrazyAirFlight()));


        mockMvc.perform(get("/busyflights?origin=ABC&destination=DEF&departureDate=2007-12-03&returnDate=2007-12-04&numberOfPassengers=1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].airline", is("WIZZ Air")))
                .andExpect(jsonPath("$[0].supplier", is("CrazyAir")))
                .andExpect(jsonPath("$[0].fare", is(9.0)))
                .andExpect(jsonPath("$[0].departureAirportCode", is("ABC")))
                .andExpect(jsonPath("$[0].destinationAirportCode", is("DEF")))
                .andExpect(jsonPath("$[0].departureDate", is("2007-12-03T00:00:00")))
                .andExpect(jsonPath("$[0].arrivalDate", is("2007-12-04T00:00:00")))
                .andExpect(jsonPath("$[1].airline", is("Lufthansa")))
                .andExpect(jsonPath("$[1].supplier", is("ToughJet")))
                .andExpect(jsonPath("$[1].fare", is(10.9)))
                .andExpect(jsonPath("$[1].departureAirportCode", is("ABC")))
                .andExpect(jsonPath("$[1].destinationAirportCode", is("DEF")))
                .andExpect(jsonPath("$[1].departureDate", is("2007-12-03T13:30:00Z")))
                .andExpect(jsonPath("$[1].arrivalDate", is("2007-12-04T16:30:00Z")));

    }

}
