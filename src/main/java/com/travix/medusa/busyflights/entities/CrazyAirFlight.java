package com.travix.medusa.busyflights.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.ZonedDateTime;


@Entity
@Table(name = "crazy_air_flight")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrazyAirFlight {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String airline;

    @NotNull
    private BigDecimal price;

    @NotNull
    @Column(length = 1)
    private String cabinClass;

    @Column(length = 3)
    @NotNull
    private String departureAirportCode;

    @Column(length = 3)
    @NotNull
    private String destinationAirportCode;

    @NotNull
    private ZonedDateTime departureDate;

    @NotNull
    private ZonedDateTime arrivalDate;

    @NotNull
    private Integer numberOfAvailableSeats;
}
