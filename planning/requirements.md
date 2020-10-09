* Summary of Requirements
    * High Level Requirements
        * [ ] Administrator can view existing reservations by host
        * [ ] Administrator can create a reservation for a guest with a host
        * [ ] Administrator can edit an existing reservation
        * [ ] Administrator can cancel a future reservation
    * Technical Requirements
        * [x] Must be a Maven project
        * [x] Spring dependency injection with XML or Annotations
        * [ ] Financial math done using BigDecimal
        * [ ] Dates must be LocalDate, not strings
        * [ ] All file data must be represented by models in the application
        * [ ] Reservation identifiers are unique per host, not across entire application
            * [ ] Combination of reservation identifier and host identifier are required to uniquely identify a reservation
