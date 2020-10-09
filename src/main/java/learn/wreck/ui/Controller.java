package learn.wreck.ui;

import learn.wreck.models.Guest;
import learn.wreck.models.Host;
import learn.wreck.models.Reservation;
import learn.wreck.repository.DataException;
import learn.wreck.service.GuestService;
import learn.wreck.service.HostService;
import learn.wreck.service.ReservationService;
import learn.wreck.service.Result;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Component
public class Controller {

    private final ReservationService reservationService;
    private final HostService hostService;
    private final GuestService guestService;
    private final View view;

    public Controller(ReservationService reservationService, HostService hostService, GuestService guestService, View view) {
        this.reservationService = reservationService;
        this.hostService = hostService;
        this.guestService = guestService;
        this.view = view;
    }

    public void run() {
        view.displayHeader("Welcome");
        try {
            runMenuOptions();
        } catch (DataException ex) {
            view.displayException(ex);
        }
        view.displayHeader("Goodbye.");
    }

    public void runMenuOptions() throws DataException {
        MainMenuOption option;
        do {
            option = view.selectMainMenuOption();
            switch (option) {
                case VIEW_RESERVATIONS:
                    viewReservations();
                    break;
                case MAKE_RESERVATION:
                    makeReservation();
                    break;
                case EDIT_RESERVATION:
                    editReservation();
                    break;
                case CANCEL_RESERVATION:
                    cancelReservation();
                    break;
            }
        } while (option != MainMenuOption.EXIT);
    }

    private void viewReservations() {
        view.displayHeader("View Reservations By Host");

        Host host = findHostByEmail();
        displayReservationsByHost(host);
    }

    private void makeReservation() throws DataException {
        view.displayHeader("Make a Reservation");

        Host host = findHostByEmail();
        String hostEmail = host.getEmailAddress();
        Guest guest = findGuestByEmail();
        int guestID = guest.getGuestID();
        displayReservationsByHost(host);
        LocalDate startDate = view.readDate("Start (MM/dd/yyyy): ");
        LocalDate endDate = view.readDate("End (MM/dd/yyyy): ");

        Reservation reservation = new Reservation();
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setGuestID(guestID);
        reservation.setTotalPrice(calculatePrice(host, startDate, endDate));

        Result<Reservation> result = reservationService.makeReservation(reservation, hostEmail);
        if (result.isSuccess()) {
            view.displayHeader("Summary");
            view.displaySummary(reservation);
            result = view.getConfirmation();
        }


        if (result.isSuccess()) {
            result = reservationService.setReservation(reservation, hostEmail);
            String successMessage = String.format("Reservation %s made successfully", result.getPayload().getReservationID());
            view.displayStatus(true, successMessage);
        }
    }

    private void editReservation() {
    }

    private void cancelReservation() {
    }





    // private methods

    private BigDecimal calculatePrice(Host host, LocalDate startDate, LocalDate endDate) {
        BigDecimal price = new BigDecimal(0);
        for (;startDate.isBefore(endDate); startDate = startDate.plusDays(1)) {
            if (startDate.getDayOfWeek() == DayOfWeek.SATURDAY || startDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                price = price.add(host.getWeekendRate());
            } else {
                price = price.add(host.getStandardRate());
            }
        }
        return price;
    }

    private void displayReservationsByHost(Host host) {
        String hostEmail = host.getEmailAddress();
        view.displayHeader(host.getLastName() + ": " + host.getCity() + ", " + host.getState());
        List<Reservation> reservations = reservationService.viewReservationByHost(hostEmail);
        List<Guest> guests = guestService.findAll();
        List<Reservation> orderedReservations = view.orderReservations(reservations);
        view.formatReservations(orderedReservations, guests);
    }

    private Host findHostByEmail() {
        Host host = null;
        String hostEmail;
        while (host == null) {
            hostEmail = view.readEmail("Host Email: ");
            host = hostService.findByEmail(hostEmail);
        }
        return host;
    }

    private Guest findGuestByEmail() {
        Guest guest = null;
        String guestEmail;
        while (guest == null) {
            guestEmail = view.readEmail("Guest email: ");
            guest = guestService.findByEmail(guestEmail);
        }
        return guest;
    }

}
