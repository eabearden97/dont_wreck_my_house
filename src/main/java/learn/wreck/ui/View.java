package learn.wreck.ui;

import learn.wreck.models.Guest;
import learn.wreck.models.Reservation;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class View {

    private final ConsoleIO io;

    public View(ConsoleIO io) {
        this.io = io;
    }

    public MainMenuOption selectMainMenuOption() {
        displayHeader("Main Menu");
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (MainMenuOption option : MainMenuOption.values()) {
            io.printf("%s. %s%n", option.getValue(), option.getOptionTitle());
            min = Math.min(min, option.getValue());
            max = Math.max(max, option.getValue());
        }

        String message = String.format("Select [%s-%s]: ", min, max);
        return MainMenuOption.fromValue(io.readInt(message));
    }

    public void displayHeader(String message) {
        io.println("");
        io.println(message);
        io.println("=".repeat(message.length()));
    }

    public void displayException(Exception ex) {
        displayHeader("A critical error has occurred:");
        io.println(ex.getMessage());
    }

    public void formatReservations(List<Reservation> reservations, List<Guest> guests) {
        for (Reservation reservation : reservations) {
            String firstName = null;
            String lastName = null;
            String guestEmail = null;
            for (Guest guest : guests) {
                if (guest.getGuestID() == reservation.getGuestID()) {
                    firstName = guest.getFirstName();
                    lastName = guest.getLastName();
                    guestEmail = guest.getEmailAddress();
                }
            }
            String formattedReservation = String.format("ID: %d, %s - %s, Guest: %s, %s, Email: %s",
                    reservation.getReservationID(),
                    reservation.getStartDate().toString(),
                    reservation.getEndDate().toString(),
                    firstName,
                    lastName,
                    guestEmail);
            io.println(formattedReservation);
        }
    }

    public List<Reservation> orderReservations(List<Reservation> reservations) {
        List<Reservation> orderedReservations = reservations.stream()
                .sorted(Comparator.comparing(Reservation::getStartDate))
                .collect(Collectors.toList());

        return orderedReservations;
    }

    public String readHostEmail() {
        return io.readRequiredString("Host Email: ");
    }





}
