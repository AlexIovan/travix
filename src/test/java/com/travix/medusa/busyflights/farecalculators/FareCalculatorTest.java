package com.travix.medusa.busyflights.farecalculators;

import com.travix.medusa.busyflights.utils.farecalculators.FareCalculator;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class FareCalculatorTest {

    @Test
    public void testComputeToughJetFinalPrice() {
        ToughJetResponse toughJetResponse = ToughJetResponse.builder().basePrice(20).discount(25).tax(3).build();
        double toughJetFinalPrice = FareCalculator.computeToughJetFinalPrice(toughJetResponse, 1);
        assertEquals(18, toughJetFinalPrice, 0);

        toughJetFinalPrice = FareCalculator.computeToughJetFinalPrice(toughJetResponse, 2);
        assertEquals(36, toughJetFinalPrice, 0);
    }

    @Test
    public void testComputeCrazyAirFinalPrice() {
        assertEquals(24, FareCalculator.computeCrazyAirFinalPrice(24, 1), 0);
        assertEquals(48, FareCalculator.computeCrazyAirFinalPrice(24, 2), 0);
    }
}
