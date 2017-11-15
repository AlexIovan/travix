Test Request URL: http://localhost:8080/busyflights?origin=GhI&destination=JKL&departureDate=2007-12-03&returnDate=2007-12-04&numberOfPassengers=1

Files Changed: The Request and Response objects were extended with validation annotations and the lombok annotations for generating 
getters, setters, constructors, etc.

Solution Structure

    - BusyFlightsRestController - entry point of the application which serves the incoming BusyFlights requests
    - CrazyAirFlightRestController. ToughJetRestController - servers the request for flights to CrazyAir and ToughJet, respectively
    - ExceptionHandlerController - handles the potential exceptions and returns a non-technical, readable message
    
    - CrazyAirFlight(-Repository), ToughJetFlight(-Repository) - entities and daos for the two search services
    
    - package utils.loaders - inserts data at application start, for testing requests
    - package utils.converters - helper classes for translating response objects and entities
    - package test - test classes for rest controllers, daos and fare calculator helper class
