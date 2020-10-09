package learn.wreck.ui;

import org.springframework.stereotype.Component;

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


}
