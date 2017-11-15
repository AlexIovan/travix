package com.travix.medusa.busyflights.controllers;

import com.travix.medusa.busyflights.controllers.rest.CrazyAirFlightRestController;
import com.travix.medusa.busyflights.utils.loaders.CrazyAirFlightBuilder;
import com.travix.medusa.busyflights.repositories.CrazyAirFlightRepository;
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
@WebMvcTest(value = CrazyAirFlightRestController.class, secure = false)
public class CrazyAirFlightRestControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CrazyAirFlightRepository crazyAirFlightRepository;

    @Test
    public void testRetrieveCrazyAirFlights() throws Exception {
        Mockito.when(
                crazyAirFlightRepository.findFlights("ABC", "DEF", ZonedDateTime.parse("2007-12-03T00:00:00+00:00"),
                        ZonedDateTime.parse("2007-12-04T00:00:00+00:00"), 1)).
                thenReturn(Collections.singletonList(CrazyAirFlightBuilder.generateCrazyAirFlight()));

        mockMvc.perform(get("/crazyair/flights?origin=ABC&destination=DEF&departureDate=2007-12-03&returnDate=2007-12-04&passengerCount=1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].airline", is("WIZZ Air")))
                .andExpect(jsonPath("$[0].price", is(10.0)))
                .andExpect(jsonPath("$[0].cabinClass", is("C")))
                .andExpect(jsonPath("$[0].departureAirportCode", is("ABC")))
                .andExpect(jsonPath("$[0].destinationAirportCode", is("DEF")))
                .andExpect(jsonPath("$[0].departureDate", is("2007-12-03T00:00:00")))
                .andExpect(jsonPath("$[0].arrivalDate", is("2007-12-04T00:00:00")));

    }

}
