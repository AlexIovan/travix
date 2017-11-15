package com.travix.medusa.busyflights.repositories;

import com.travix.medusa.busyflights.entities.CrazyAirFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Collection;

@Repository
public interface CrazyAirFlightRepository extends JpaRepository<CrazyAirFlight, Long> {

    /**
     * repository method to search the CrazyAirFlight entries by the given parameters
     *
     * @param departureAirportCode    departureAirportCode
     * @param arrivalAirportCode      arrivalAirportCode
     * @param departureDateBeginOfDay departureDateBeginOfDay
     * @param departureDateEndOfDay   departureDateEndOfDay
     * @param arrivalDateBeginOfDay   arrivalDateBeginOfDay
     * @param arrivalDateEndOfDay     arrivalDateEndOfDay
     * @param numberOfPassengers      numberOfPassengers
     * @return a list of entries matching the specified search criteria
     */
    Collection<CrazyAirFlight> findByDepartureAirportCodeIgnoreCaseAndDestinationAirportCodeIgnoreCaseAndDepartureDateBetweenAndArrivalDateBetweenAndNumberOfAvailableSeatsGreaterThanEqual
    (String departureAirportCode, String arrivalAirportCode, ZonedDateTime departureDateBeginOfDay, ZonedDateTime departureDateEndOfDay, ZonedDateTime arrivalDateBeginOfDay, ZonedDateTime arrivalDateEndOfDay, Integer numberOfPassengers);

    /**
     * repository method with a shortened name to search the CrazyAirFlight entries by the given parameters.
     * Prior to searching by dates, it constructs a pair of parameters with minimum and maximum hours/minutes
     * to include all flights happening in a day, regardless of hours/minutes
     *
     * @param departureAirportCode departureAirportCode
     * @param arrivalAirportCode   arrivalAirportCode
     * @param departureDate        departureDate
     * @param arrivalDate          arrivalDate
     * @param numberOfPassengers   numberOfPassengers
     * @return a list of entries matching the specified search criteria
     */
    default Collection<CrazyAirFlight> findFlights(String departureAirportCode, String arrivalAirportCode, ZonedDateTime departureDate, ZonedDateTime arrivalDate, Integer numberOfPassengers) {
        ZonedDateTime departureDateBeginOfDay = departureDate.with(LocalTime.MIN);
        ZonedDateTime departureDateEndOfDay = departureDate.with(LocalTime.MAX);
        ZonedDateTime arrivalDateBeginOfDay = arrivalDate.with(LocalTime.MIN);
        ZonedDateTime arrivalDateEndOfDay = arrivalDate.with(LocalTime.MAX);
        return findByDepartureAirportCodeIgnoreCaseAndDestinationAirportCodeIgnoreCaseAndDepartureDateBetweenAndArrivalDateBetweenAndNumberOfAvailableSeatsGreaterThanEqual
                (departureAirportCode, arrivalAirportCode, departureDateBeginOfDay, departureDateEndOfDay, arrivalDateBeginOfDay, arrivalDateEndOfDay, numberOfPassengers);
    }
}
