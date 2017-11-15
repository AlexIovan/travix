package com.travix.medusa.busyflights.repositories;

import com.travix.medusa.busyflights.entities.ToughJetFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Collection;

@Repository
public interface ToughJetFlightRepository extends JpaRepository<ToughJetFlight, Long> {

    /**
     * repository method to search the ToughJetFlight entries by the given parameters
     *
     * @param departureAirportName       departureAirportName
     * @param arrivalAirportName         arrivalAirportName
     * @param outboundDateTimeBeginOfDay outboundDateTimeBeginOfDay
     * @param outboundDateTimeEndOfDay   outboundDateTimeEndOfDay
     * @param inboundDateTimeBeginOfDay  inboundDateTimeBeginOfDay
     * @param inboundDateTimeEndOfDay    inboundDateTimeEndOfDay
     * @param numberOfPassengers         numberOfPassengers
     * @return a list of entries matching the specified search criteria
     */
    Collection<ToughJetFlight> findByDepartureAirportNameIgnoreCaseAndArrivalAirportNameIgnoreCaseAndOutboundDateTimeBetweenAndInboundDateTimeBetweenAndNumberOfAvailableSeatsGreaterThanEqual
    (String departureAirportName, String arrivalAirportName, ZonedDateTime outboundDateTimeBeginOfDay, ZonedDateTime outboundDateTimeEndOfDay,
     ZonedDateTime inboundDateTimeBeginOfDay, ZonedDateTime inboundDateTimeEndOfDay, Integer numberOfPassengers);

    /**
     * repository method with a shortened name to search the ToughJetFlight entries by the given parameters.
     * Prior to searching by dates, it constructs a pair of parameters with minimum and maximum hours/minutes
     * to include all flights happening in a day, regardless of hours/minutes
     *
     * @param departureAirportName departureAirportName
     * @param arrivalAirportName   arrivalAirportName
     * @param outboundDateTime     outboundDateTime
     * @param inboundDatetime      inboundDatetime
     * @param numberOfPassengers   numberOfPassengers
     * @return a list of entries matching the specified search criteria
     */
    default Collection<ToughJetFlight> findFlights(String departureAirportName, String arrivalAirportName, ZonedDateTime outboundDateTime, ZonedDateTime inboundDatetime, Integer numberOfPassengers) {
        ZonedDateTime outboundDateTimeBeginOfDay = outboundDateTime.with(LocalTime.MIN);
        ZonedDateTime outboundDateTimeEndOfDay = outboundDateTime.with(LocalTime.MAX);
        ZonedDateTime inboundDateTimeBeginOfDay = inboundDatetime.with(LocalTime.MIN);
        ZonedDateTime inboundDateTimeEndOfDay = inboundDatetime.with(LocalTime.MAX);

        return findByDepartureAirportNameIgnoreCaseAndArrivalAirportNameIgnoreCaseAndOutboundDateTimeBetweenAndInboundDateTimeBetweenAndNumberOfAvailableSeatsGreaterThanEqual
                (departureAirportName, arrivalAirportName, outboundDateTimeBeginOfDay, outboundDateTimeEndOfDay, inboundDateTimeBeginOfDay, inboundDateTimeEndOfDay, numberOfPassengers);
    }
}
