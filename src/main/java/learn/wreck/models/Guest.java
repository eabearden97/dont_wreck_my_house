package learn.wreck.models;

public class Guest {
    private int guestID;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String state;

    public Guest(int guestID, String firstName, String lastName, String emailAddress, String phoneNumber, String state) {
        this.guestID = guestID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.state = state;
    }
    public Guest() {
    }

    public int getGuestID() {
        return guestID;
    }
    public void setGuestID(int guestID) {
        this.guestID = guestID;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
}
