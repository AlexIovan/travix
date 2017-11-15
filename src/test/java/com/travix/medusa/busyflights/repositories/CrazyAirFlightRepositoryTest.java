package com.travix.medusa.busyflights.repositories;

import com.travix.medusa.busyflights.entities.CrazyAirFlight;
import com.travix.medusa.busyflights.utils.loaders.CrazyAirFlightBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CrazyAirFlightRepositoryTest {

    @Autowired
    private CrazyAirFlightRepository crazyAirFlightRepository;

    @Before
    public void insertFlights() {
        crazyAirFlightRepository.save(CrazyAirFlightBuilder.generateCrazyAirFlight());
    }

    @Test
    public void testReadCrazyAirFlightsFound() {
        Collection<CrazyAirFlight> toughJetFlights = crazyAirFlightRepository.findFlights
                ("ABC", "DEF", ZonedDateTime.parse("2007-12-03T00:00:00+00:00"),
                        ZonedDateTime.parse("2007-12-04T00:00:00+00:00"), 1);

        assertNotNull(toughJetFlights);
        assertEquals(1, toughJetFlights.size());

    }

    @Test
    public void testReadCrazyAirFlightsNotFound() {
        Collection<CrazyAirFlight> toughJetFlights = crazyAirFlightRepository.findFlights
                ("ABCD", "DEF", ZonedDateTime.parse("2007-12-03T00:00:00+00:00"),
                        ZonedDateTime.parse("2007-12-04T00:00:00+00:00"), 1);
        assertNotNull(toughJetFlights);
        assertEquals(0, toughJetFlights.size());
    }
}
