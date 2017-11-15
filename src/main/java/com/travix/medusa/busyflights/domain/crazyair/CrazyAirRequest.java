package com.travix.medusa.busyflights.domain.crazyair;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CrazyAirRequest {

    @NotNull
    @Size(min = 3, max = 3)
    private String origin;
    @NotNull
    @Size(min = 3, max = 3)
    private String destination;
    @NotNull
    private String departureDate;
    @NotNull
    private String returnDate;
    @NotNull
    private int passengerCount;
}
