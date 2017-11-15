package com.travix.medusa.busyflights.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "tough_jet_flight")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToughJetFlight {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String carrier;

    @Min(0)
    @NotNull
    private BigDecimal basePrice;

    @Min(0)
    @NotNull
    private BigDecimal tax;

    @Digits(integer = 3, fraction = 2)
    @NotNull
    @Min(0)
    private BigDecimal discount;

    @Column(length = 3)
    @NotNull
    private String departureAirportName;

    @Column(length = 3)
    @NotNull
    private String arrivalAirportName;

    @NotNull
    private ZonedDateTime outboundDateTime;

    @NotNull
    private ZonedDateTime inboundDateTime;

    @NotNull
    private Integer numberOfAvailableSeats;

}
