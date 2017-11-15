package com.travix.medusa.busyflights.domain.busyflights;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class BusyFlightsRequest {

    @NotNull
    @Size(min = 3, max = 3)
    private String origin;

    @NotNull
    @Size(min = 3, max = 3)
    private String destination;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String departureDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String returnDate;

    @NotNull
    private Integer numberOfPassengers;
}
