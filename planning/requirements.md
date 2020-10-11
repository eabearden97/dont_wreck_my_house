* Summary of Requirements
    * High Level Requirements
        * [x] Administrator can view existing reservations by host
        * [x] Administrator can create a reservation for a guest with a host
        * [x] Administrator can edit an existing reservation
        * [x] Administrator can cancel a future reservation
    * Technical Requirements
        * [x] Must be a Maven project
        * [x] Spring dependency injection with XML or Annotations
        * [x] Financial math done using BigDecimal
        * [x] Dates must be LocalDate, not strings
        * [x] All file data must be represented by models in the application
        * [x] Reservation identifiers are unique per host, not across entire application
            * [x] Combination of reservation identifier and host identifier are required to uniquely identify a reservation
