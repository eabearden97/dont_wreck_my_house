package learn.wreck.ui;

import learn.wreck.models.Guest;
import learn.wreck.models.Host;
import learn.wreck.models.Reservation;
import learn.wreck.repository.DataException;
import learn.wreck.service.GuestService;
import learn.wreck.service.HostService;
import learn.wreck.service.ReservationService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
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
        String hostEmail = view.readHostEmail();
        Host host = hostService.findByEmail(hostEmail);
        view.displayHeader(host.getLastName()
                + ": " + host.getCity() + ", " + host.getState());
        List<Reservation> reservations = reservationService.viewReservationByHost(hostEmail);
        ;
        List<Guest> guests = guestService.findAll();
        List<Reservation> orderedReservations = view.orderReservations(reservations);
        view.formatReservations(orderedReservations, guests);
    }

    private void makeReservation() {
    }

    private void editReservation() {
    }

    private void cancelReservation() {
    }


}
