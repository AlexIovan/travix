package com.travix.medusa.busyflights.utils.farecalculators;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class FareCalculator {
    private static final MathContext FOUR_DECIMAL_MATH_CONTEXT = new MathContext(4, RoundingMode.CEILING);

    public static double computeToughJetFinalPrice(ToughJetResponse toughJetResponse, Integer numberOfPassengers) {
        BigDecimal basePrice = BigDecimal.valueOf(toughJetResponse.getBasePrice());
        BigDecimal discount = BigDecimal.valueOf(toughJetResponse.getDiscount());
        BigDecimal tax = BigDecimal.valueOf(toughJetResponse.getTax());

        BigDecimal discountAmountBigDecimal = basePrice.multiply(discount).divide(BigDecimal.valueOf(100), FOUR_DECIMAL_MATH_CONTEXT);
        BigDecimal discountedPriceBigDecimal = basePrice.subtract(discountAmountBigDecimal);
        BigDecimal finalPricePerPerson = discountedPriceBigDecimal.add(tax);
        BigDecimal finalPriceTotal = BigDecimal.valueOf(numberOfPassengers).multiply(finalPricePerPerson);
        return finalPriceTotal.setScale(2, RoundingMode.CEILING).doubleValue();
    }

    public static double computeCrazyAirFinalPrice(double price, Integer numberOfPassengers) {
        return BigDecimal.valueOf(price).multiply(BigDecimal.valueOf(numberOfPassengers).setScale(2, RoundingMode.CEILING)).doubleValue();
    }
}
