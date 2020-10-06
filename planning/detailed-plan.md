* Detailed Task List
    * ====================================================
    * [x] Planning (3 hours)
        * [x] Create a markdown with a basic task list
            * [x] Include time estimations
            * [x] Include list of deliverables
        * [x] Create a markdown with a list of project requirements
            * [x] Include high-level requirements
            * [x] Include technical requirements
        * [x] Create a markdown with very detailed implementation instructions
            * [x] Include the basic task list with expanded instructions for implementation
                * should serve as a guide moving forward for specific implementation details
    * ====================================================
    * [x] Build Models (2 hours)
        * Glossary of Models
            * Guest
                * A customer, someone who wants to book a place to stay
                * Data provided
            * Host
                * Accommodation provider, someone who has a property to rent per night
                * Data provided
            * Location
                * A rental property, combined with host in this app
                    * limit of one Location per Host
                    * in other words, these are the same thing for our purposes
            * Reservation
                * One or more days where a Guest has exclusive access to a Location/Host
                * data provided
            * Administrator
                * The user of the application
                * books reservations for the Guests and Hosts
        * [x] Download data files and put in a data folder in project
            * [x] Populate "Create model classes" task with variables name and types
        * [x] Add JUnit dependency to pom file
        * [x] Add Spring dependency to pom file
        * [x] Create folders for models, data, domain, ui, and data
        * [x] Create model classes
            * [x] Create Guest class
                * [x] int guestID (1,2,3, etc.)
                * [x] String firstName
                * [x] String lastName
                * [x] String email
                * [x] String phone
                * [x] String state
            * [x] Create Host/Location class
                * [x] int hostID (36 digit GUID)
                * [x] String lastName
                * [x] String email
                * [x] String phone
                * [x] String address
                * [x] String city
                * [x] String state
                * [x] int postalCode
                * [x] BigDecimal standardRate
                * [x] BigDecimal weekendRate
            * [x] Create Reservation class
                * [x] int reservationID (1,2,3, etc.)
                * [x] LocalDate startDate
                * [x] LocalDate endDate
                * [x] int guestID (1,2,3, etc.)
                * [x] BigDecimal total
            * [x] for each class, add necessary variables, constructors, getters, setters
    * ====================================================
    * [ ] Build Repository Layer (4 hours)
        * [x] make a DataException class
            * extends Exception
        * [x] make a ReservationRepository interface
        * [ ] make a ReservationFileRepository class that implements the ReservationRepository interface
            * Methods (CRUD Operations)
                * [ ] Create/Make Reservation
                * [ ] Read/View Reservation
                * [ ] Update/Edit Reservation
                * [ ] Remove/Cancel Reservation
        * [x] make a Host_LocationRepository interface
        * [ ] make a Host_LocationFileRepository class that implements the Host_LocationRepository interface
            * Methods (CRUD Operations)
                * Read/View Host
                    * [ ] findAll()
                    * [ ] findByEmail()
                    * [ ] findByID()
        * [x] make a GuestRepository interface
        * [x] make a GuestFileRepository class that implements the Host_LocationRepository interface
            * Methods (CRUD Operations)
                * Read/View Guest
                    * [ ] findAll()
                    * [ ] findByEmail()
                    * [ ] findByID()
    * ====================================================
    * [ ] Test Data Layer (2 hours)
        * 
        * [ ] Create Seed Filepath for data
        * [ ] Create Test Filepath for data
            * [ ] Use @BeforeEach to copy over elements from seed to test
        * [ ] Tests
            * [ ] Create ReservationFileRepositoryTest
                * [ ] shouldMakeReservation
                * [ ] shouldNotMakeReservation
                    * there will be several of these (under what conditions should it not make the reservation?)
                * [ ] shouldViewReservation
                * [ ] shouldNotViewReservation
                    * there will be several of these (under what conditions should it not make the reservation?)
                * [ ] shouldEditReservation
                * [ ] shouldNotEditReservation
                    * there will be several of these (under what conditions should it not make the reservation?)
                * [ ] shouldCancelReservation
                * [ ] shouldNotCancelReservation
                    * there will be several of these (under what conditions should it not make the reservation?)
    * ====================================================
    * [ ] Build Domain Layer (4 hours)
        * [ ] Make ReservationService
            * [ ] Methods
                * [ ] makeReservation
                * [ ] viewReservation
                * [ ] editReservation
                * [ ] cancelReservation
                * [ ] validate
                    * [ ] validateNulls
                    * [ ] validateFields
                    * [ ] validateChildrenExist
        * [ ] Make Host_LocationService
            * [ ] Methods
                * [ ] findAll
                * [ ] findByEmail
                * [ ] findByID
                * [ ] validate
                    * [ ] validateNulls
                    * [ ] validateFields
                    * [ ] validateChildrenExist
        * [ ] Make GuestService
            * [ ] Methods
                * [ ] findAll
                * [ ] findByEmail
                * [ ] findByID
                * [ ] validate
                    * [ ] validateNulls
                    * [ ] validateFields
                    * [ ] validateChildrenExist
        * [ ] Make Response class
        * [ ] Make Result class
    * ====================================================
    * [ ] Test Domain Layer (3 hours)
        * [ ] Make ReservationFileRepositoryDouble
            * [ ] shouldMakeReservation
            * [ ] shouldNotMakeReservation
                * there will be several of these (under what conditions should it not make the reservation?)
            * [ ] shouldViewReservation
            * [ ] shouldNotViewReservation
                * there will be several of these (under what conditions should it not make the reservation?)
            * [ ] shouldEditReservation
            * [ ] shouldNotEditReservation
                * there will be several of these (under what conditions should it not make the reservation?)
            * [ ] shouldCancelReservation
            * [ ] shouldNotCancelReservation
                * there will be several of these (under what conditions should it not make the reservation?)
            * [ ] shouldValidate
            * [ ] shouldNotValidate
                * [ ] one for each possible null value that shouldn't be null
                * [ ] one or more for each field that could have an invalid value
                * [ ] one for each child (repository) that shouldn't be null
        * [ ] Make Host_LocationFileRepositoryDouble
            * [ ] shouldFindAll
            * [ ] shouldFindByEmail
            * [ ] shouldNotMissingEmail
            * [ ] shouldFindByID
            * [ ] shouldNotFindMissingID
            * [ ] shouldNotFindInvalidID
            * [ ] shouldValidate
            * [ ] shouldNotValidate
                * [ ] one for each possible null value that shouldn't be null
                * [ ] one or more for each field that could have an invalid value
                * [ ] one for each child (repository) that shouldn't be null
        * [ ] Make GuestFileRepositoryDouble
            * [ ] shouldFindAll
            * [ ] shouldFindByEmail
            * [ ] shouldNotMissingEmail
            * [ ] shouldFindByID
            * [ ] shouldNotFindMissingID
            * [ ] shouldNotFindInvalidID
            * [ ] shouldValidate
            * [ ] shouldNotValidate
                * [ ] one for each possible null value that shouldn't be null
                * [ ] one or more for each field that could have an invalid value
                * [ ] one for each child (repository) that shouldn't be null
    * ====================================================
    * [ ] Build UI Layer (4 hours)
        * [ ] Make Controller class
            * Methods
                * run()
                * runAppLoop()
                * viewReservations()
                * makeReservation()
                * editReservation()
                * cancelReservation()
        * [ ] Make View class
            * connection between Controller and ConsoleIO class
            * take input/output from ConsoleIO class and put results in controller class
        * [ ] Make ConsoleIO class
            * all input and output methods should be here
            * anything that the user sees
            * anything that is read
        * [ ] Make MainMenuOption enumeration
            * EXIT
            * VIEW_RESERVATIONS
            * MAKE_RESERVATION
            * EDIT_RESERVATION
            * CANCEL_RESERVATION
        * Notes from LMS
            * Main Menu has 5 options
            
                * Exit
                
                * View Reservations for Host
                    * enter a Host email
                        * if Host not found, display a message
                        * if Host has no reservations, display a message
                    * this produces all reservations for that Host/Location
                        * show the Guest, dates, totals, etc. for each reservation
                        * sort reservations by date
                        
                * Make a Reservation
                    * enter a Guest email (required)
                        * if Guest not found, display a message
                    * enter a Host email (required)
                        * if Host not found, display a message
                    * this produces all existing reservations for that Host/Location
                    * enter a start date (required)
                        * must be in the future
                    * enter an end date (required)
                        * start date must come before end date
                        * dates cannot overlap existing reservations dates
                    * produce a summary
                        * start date
                        * end date
                        * total price
                            * based on weekday and weekend rates
                        * "Is this okay? [y/n]"
                    * display success/failure message
                        * if successful, save the reservation in data
                        
                * Edit a Reservation
                    * Note: allow users to press "Enter" to keep existing data
                    * enter a Guest email (required)
                        * if Guest not found, display a message
                    * enter a Host email (required)
                    * this produces all existing reservation for that Host/Location and Guest combo
                    * enter a reservation ID
                    * display start date and allow user to enter a new one
                        * must be in the future
                    * display end date and allow user to enter a new one
                        * start date must come before end date
                        * dates cannot overlap existing reservations dates
                    * produce a summary
                        * start date
                        * end date
                        * total price
                        * "Is this okay? [y/n]"
                    * display success/failure message
                    
                * Cancel a Reservation
                    * enter a Host email
                        * if Host not found, display a message
                        * if Host has no reservations, display a message
                    * this produces all reservations for that Host/Location
                        * only display future reservation (cannot cancel past reservation)
                        * show the Guest, dates, totals, etc. for each reservation
                        * sort reservations by date
                    * enter a reservation ID
                        * display success/failure message
        * [ ] Make App class
            * should just have a few line
                * make new Controller object
                * call controller.run()
        * [ ] Add Spring DI Annotations
    * ====================================================
    * [ ] Debugging (4 hours)
    * ====================================================
    * [ ] Code Improvements/Refactoring (4 hours)
        * [ ] delete unnecessary methods
        * [ ] delete code that is commented out
        * [ ] delete unnecessary imports
    * ====================================================
    * [ ] Miscellaneous Time (4 hours)
    * ====================================================