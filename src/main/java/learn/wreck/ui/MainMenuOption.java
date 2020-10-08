package learn.wreck.ui;

public enum MainMenuOption {
    MAKE_RESERVATION(1, "Make Reservation"),
    EDIT_RESERVATION(2, "Edit Reservation"),
    CANCEL_RESERVATION(3, "Cancel a Reservation"),
    EXIT(4, "Exit");

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
