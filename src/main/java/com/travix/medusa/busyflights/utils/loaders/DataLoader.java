package com.travix.medusa.busyflights.utils.loaders;

import com.travix.medusa.busyflights.repositories.CrazyAirFlightRepository;
import com.travix.medusa.busyflights.repositories.ToughJetFlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataLoader implements ApplicationRunner {

    private final ToughJetFlightRepository toughJetFlightRepository;
    private final CrazyAirFlightRepository crazyAirFlightRepository;


    @Autowired
    public DataLoader(ToughJetFlightRepository toughJetFlightRepository, CrazyAirFlightRepository crazyAirFlightRepository) {
        this.toughJetFlightRepository = toughJetFlightRepository;
        this.crazyAirFlightRepository = crazyAirFlightRepository;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        toughJetFlightRepository.save(ToughJetFlightBuilder.generateToughJetFlight());
        toughJetFlightRepository.save(ToughJetFlightBuilder.generateToughJetFlight("RyanAir", new BigDecimal(40),
                new BigDecimal(10), new BigDecimal(25), "GHI", "JKL",
                "2007-12-03T00:00:00+00:00", "2007-12-04T00:00:00+00:00", 120));
        toughJetFlightRepository.save(ToughJetFlightBuilder.generateToughJetFlight("TAROM", new BigDecimal(140),
                new BigDecimal(20), new BigDecimal(20), "GHI", "JKL",
                "2007-12-03T00:00:00+00:00", "2007-12-04T00:00:00+00:00", 120));
        toughJetFlightRepository.save(ToughJetFlightBuilder.generateToughJetFlight("Turkish Airlines", new BigDecimal(240),
                new BigDecimal(10), new BigDecimal(55), "MNO", "PQR",
                "2008-12-03T00:00:00+00:00", "2008-12-04T00:00:00+00:00", 120));
        toughJetFlightRepository.save(ToughJetFlightBuilder.generateToughJetFlight("British Airways", new BigDecimal(340),
                new BigDecimal(10), new BigDecimal(25), "MNO", "PQR",
                "2008-12-03T00:00:00+00:00", "2008-12-04T00:00:00+00:00", 420));

        crazyAirFlightRepository.save(CrazyAirFlightBuilder.generateCrazyAirFlight());
        crazyAirFlightRepository.save(CrazyAirFlightBuilder.generateCrazyAirFlight("Blue Air", new BigDecimal(110),
                "B", "GHI", "JKL", "2007-12-03",
                "2007-12-04", 120));
        crazyAirFlightRepository.save(CrazyAirFlightBuilder.generateCrazyAirFlight("Qatar Airways", new BigDecimal(230),
                "B", "GHI", "JKL", "2007-12-03",
                "2007-12-04", 150));
        crazyAirFlightRepository.save(CrazyAirFlightBuilder.generateCrazyAirFlight("Fly Emirates", new BigDecimal(87),
                "E", "MNO", "PQR", "2008-12-03",
                "2008-12-04", 220));
        crazyAirFlightRepository.save(CrazyAirFlightBuilder.generateCrazyAirFlight("Blue Air", new BigDecimal(110),
                "B", "MNO", "PQR", "2008-12-03",
                "2008-12-04", 320));

    }


}
