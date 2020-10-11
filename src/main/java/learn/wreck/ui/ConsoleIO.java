package learn.wreck.ui;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.IllegalFormatConversionException;
import java.util.Optional;
import java.util.Scanner;

@Component
public class ConsoleIO {

    private final Scanner scanner = new Scanner(System.in);

    public void print(String message) {
        System.out.print(message);
    }
    public void println(String message) {
        System.out.println(message);
    }
    public void printf(String format, Object... values) {
        System.out.printf(format, values);
    }


    public int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readRequiredString(prompt));
            } catch (NumberFormatException ex) {
                println("Error: Enter a valid number");
            }
        }
    }

    public int readInt(String prompt, int min, int max) {
        while (true) {
            try {
                int result = Integer.parseInt(readRequiredString(prompt));
                if (result >= min && result <= max) {
                    return result;
                }
            } catch (NumberFormatException ex) {
                printf("Error: Enter a valid number between %d and %d.%n", min, max);
            }
        }
    }

    public String readString(String prompt) {
        print(prompt);
        return scanner.nextLine();
    }

    public String readRequiredString(String prompt) {
        while (true) {
            String result = readString(prompt);
            if (!result.isBlank()) {
                return result;
            }
            println("Error: This field is required.");
        }
    }

    public LocalDate readRequiredDate(String prompt) {
        while (true) {
            String input = readRequiredString(prompt);
            try {
                LocalDate result = LocalDate.parse(input, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                return result;
            } catch (DateTimeParseException ex) {
                println("Please enter date in MM/dd/yyyy format.");
            }
        }
    }

    public LocalDate readDate(String prompt) {
        while (true) {
            String input = readString(prompt);
            if (input.trim().equalsIgnoreCase("")) {
                return null;
            }
            try {
                return LocalDate.parse(input, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            } catch (DateTimeParseException ex) {
                println("Please enter date in MM/dd/yyyy format.");
            }
        }
    }


}
