package com.travix.medusa.busyflights.controllers;

import com.travix.medusa.busyflights.controllers.rest.ToughJetFlightRestController;
import com.travix.medusa.busyflights.utils.loaders.ToughJetFlightBuilder;
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
@WebMvcTest(value = ToughJetFlightRestController.class, secure = false)
public class ToughJetFlightRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToughJetFlightRepository toughJetFlightRepository;

    @Test
    public void testRetrieveToughJetFlights() throws Exception {
        Mockito.when(
                toughJetFlightRepository.findFlights("ABC", "DEF", ZonedDateTime.parse("2007-12-03T00:00:00+00:00"),
                        ZonedDateTime.parse("2007-12-04T00:00:00+00:00"), 1)).
                thenReturn(Collections.singletonList(ToughJetFlightBuilder.generateToughJetFlight()));

        mockMvc.perform(get("/toughjet/flights?from=ABC&to=DEF&outboundDate=2007-12-03&inboundDate=2007-12-04&numberOfAdults=1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].carrier", is("Lufthansa")))
                .andExpect(jsonPath("$[0].basePrice", is(10.0)))
                .andExpect(jsonPath("$[0].tax", is(1.0)))
                .andExpect(jsonPath("$[0].discount", is(1.0)))
                .andExpect(jsonPath("$[0].departureAirportName", is("ABC")))
                .andExpect(jsonPath("$[0].arrivalAirportName", is("DEF")))
                .andExpect(jsonPath("$[0].outboundDateTime", is("2007-12-03T00:00:00Z")))
                .andExpect(jsonPath("$[0].inboundDateTime", is("2007-12-04T00:00:00Z")));

    }

}
