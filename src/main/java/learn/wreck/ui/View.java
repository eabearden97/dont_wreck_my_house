package learn.wreck.ui;

import learn.wreck.models.Guest;
import learn.wreck.models.Reservation;
import learn.wreck.service.Result;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
        return MainMenuOption.fromValue(io.readInt(message, min, max));
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

    public void displayStatus(boolean success, List<String> messages) {
        displayHeader(success ? "Success" : "Error");
        for (String message : messages) {
            io.println(message);
        }
    }

    public void displayStatus(boolean success, String message) {
        displayStatus(success, List.of(message));
    }

    public void formatReservations(List<Reservation> reservations, List<Guest> guests) {
        if (reservations.size() == 0) {
            io.println("There are no reservations for this host.");
        }

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
            String formattedReservation = String.format("ID: %-4d %s - %-12s Guest: %s %s ... Email: %s",
                    reservation.getReservationID(),
                    reservation.getStartDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                    reservation.getEndDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")),
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

    public String readEmail(String prompt) {
        return io.readRequiredString(prompt);
    }


    public LocalDate readRequiredDate(String prompt) {
        return io.readRequiredDate(prompt);
    }

    public LocalDate readDate(String prompt) {
        return io.readDate(prompt);
    }

    public void displaySummary(Reservation reservation) {
        io.printf("Start: %s%n", reservation.getStartDate().toString());
        io.printf("End: %s%n", reservation.getEndDate().toString());
        String formattedPrice = String.format("$%s", reservation.getTotalPrice());
        io.printf("Total: %s%n", formattedPrice);
    }

    public Result<Reservation> getConfirmation() {
        Result<Reservation> result = new Result<>();
        boolean valid = false;
        while (!valid) {
            String answer = io.readRequiredString("Is this okay? [y/n]: ");
            if (answer.equalsIgnoreCase("y")) {
                valid = true;
            } else if (answer.equalsIgnoreCase("n")) {
                valid = true;
                result.addErrorMessage("Reservation not confirmed.");
            }
        }
        return result;
    }

    public int getReservationID(String prompt, int min, int max) {
        return io.readInt(prompt, min, max);
    }





}
