package learn.wreck.ui;

public enum MainMenuOption {
    VIEW_RESERVATIONS(1, "View Existing Reservations"),
    MAKE_RESERVATION(2, "Make a Reservation"),
    EDIT_RESERVATION(3, "Edit a Reservation"),
    CANCEL_RESERVATION(4, "Cancel a Reservation"),
    EXIT(5, "Exit");

    private String optionTitle;
    private int value;

    MainMenuOption(int value, String optionTitle) {
        this.value = value;
        this.optionTitle = optionTitle;
    }

    public String getOptionTitle() {
        return optionTitle;
    }

    public int getValue() {
        return value;
    }

    public static MainMenuOption fromValue(int value) {
        for (MainMenuOption option : MainMenuOption.values()) {
            if (option.getValue() == value) {
                return option;
            }
        }
        return EXIT;
    }


}
