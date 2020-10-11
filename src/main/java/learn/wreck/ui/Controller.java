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

import java.time.LocalDate;
import java.util.ArrayList;
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
        Guest guest = findGuestByEmail();

        displayReservationsByHost(host);

        Reservation reservation = view.makeReservation(host, guest);
        finalizeReservation(reservation, host, true);
    }

    private void editReservation() throws DataException {
        view.displayHeader("Edit a Reservation");
        Host host = findHostByEmail();
        Guest guest = findGuestByEmail();

        displayReservationsByHostAndGuest(host, guest);
        List<Reservation> reservations = reservationService.viewReservationByHostAndGuest(host.getEmailAddress(), guest.getGuestID());
        int reservationID = view.chooseReservationID(reservations);

        view.displayHeader(String.format("Editing Reservation %d", reservationID));
        Reservation reservation = view.editReservation(host, guest, reservations, reservationID);
        finalizeReservation(reservation, host, false);
    }

    private void cancelReservation() throws DataException {
        view.displayHeader("Cancel a Reservation");
        Host host = findHostByEmail();
        Guest guest = findGuestByEmail();
        displayFutureReservationsByHostAndGuest(host, guest);

        List<Reservation> reservations = reservationService.viewReservationByHostAndGuest(host.getEmailAddress(), guest.getGuestID());
        int reservationID = view.chooseReservationID(reservations);

        boolean success = false;
        for (Reservation reservation : reservations) {
            if (reservation.getReservationID() == reservationID) {
                success = reservationService.cancelReservation(reservation, host.getEmailAddress());
            }
        }
        if (success) {
            String successMessage = String.format("Reservation %d cancelled.", reservationID);
            view.displayStatus(true, successMessage);
        } else {
            String failureMessage = "Something went wrong. Please try again.";
            view.displayStatus(false, failureMessage);
        }
    }



    // private methods

    private void displayReservationsByHost(Host host) {
        String hostEmail = host.getEmailAddress();
        view.displayHeader(host.getLastName() + ": " + host.getCity() + ", " + host.getState());
        List<Reservation> reservations = reservationService.viewReservationByHost(hostEmail);
        List<Guest> guests = guestService.findAll();
        List<Reservation> orderedReservations = view.orderReservations(reservations);
        view.formatReservations(orderedReservations, guests);
    }

    private void displayReservationsByHostAndGuest(Host host, Guest guest) {
        String hostEmail = host.getEmailAddress();
        view.displayHeader(host.getLastName() + ": " + host.getCity() + ", " + host.getState());
        List<Reservation> hostAndGuestReservations = new ArrayList<>();
        List<Reservation> reservations = reservationService.viewReservationByHost(hostEmail);
        for (Reservation reservation : reservations) {
            if (reservation.getGuestID() == guest.getGuestID()) {
                hostAndGuestReservations.add(reservation);
            }
        }
        List<Guest> guests = guestService.findAll();
        List<Reservation> orderedReservations = view.orderReservations(hostAndGuestReservations);
        view.formatReservations(orderedReservations, guests);
    }

    private void displayFutureReservationsByHostAndGuest(Host host, Guest guest) {
        String hostEmail = host.getEmailAddress();
        view.displayHeader(host.getLastName() + ": " + host.getCity() + ", " + host.getState());
        List<Reservation> hostAndGuestReservations = new ArrayList<>();
        List<Reservation> reservations = reservationService.viewReservationByHost(hostEmail);
        for (Reservation reservation : reservations) {
            if (reservation.getGuestID() == guest.getGuestID() && reservation.getStartDate().isAfter(LocalDate.now())) {
                hostAndGuestReservations.add(reservation);
            }
        }
        List<Guest> guests = guestService.findAll();
        List<Reservation> orderedReservations = view.orderReservations(hostAndGuestReservations);
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

    private void finalizeReservation(Reservation reservation, Host host, boolean isNew) throws DataException {
        Result<Reservation> result;
        if (isNew) {
            result = reservationService.makeReservation(reservation, host.getEmailAddress());
        } else {
            result = reservationService.editReservation(reservation, host.getEmailAddress());
        }

        if (result.isSuccess()) {
            view.displayHeader("Summary");
            view.displaySummary(reservation);
            result = view.getConfirmation();
        }

        if (result.isSuccess()) {
            if (isNew) {
                result = reservationService.setReservation(reservation, host.getEmailAddress());
                String successMessage = String.format("Reservation %s made successfully", result.getPayload().getReservationID());
                view.displayStatus(true, successMessage);
            } else {
                String successMessage = String.format("Reservation %s edited successfully", reservation.getReservationID());
                view.displayStatus(true, successMessage);
            }
        } else {
            view.displayStatus(false, result.getMessages());
        }
    }

}
