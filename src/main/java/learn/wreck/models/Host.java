package learn.wreck.models;

import java.math.BigDecimal;

public class Host {
    private String hostID;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String streetAddress;
    private String city;
    private String state;
    private int postalCode;
    private BigDecimal standardRate;
    private BigDecimal weekendRate;

    public Host(String hostID, String lastName, String emailAddress, String phoneNumber, String streetAddress, String city, String state, int postalCode, BigDecimal standardRate, BigDecimal weekendRate) {
        this.hostID = hostID;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.standardRate = standardRate;
        this.weekendRate = weekendRate;
    }

    public Host() {

    }

    public String getHostID() {
        return hostID;
    }
    public void setHostID(String hostID) {
        this.hostID = hostID;
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
    public String getStreetAddress() {
        return streetAddress;
    }
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public int getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
    public BigDecimal getStandardRate() {
        return standardRate;
    }
    public void setStandardRate(BigDecimal standardRate) {
        this.standardRate = standardRate;
    }
    public BigDecimal getWeekendRate() {
        return weekendRate;
    }
    public void setWeekendRate(BigDecimal weekendRate) {
        this.weekendRate = weekendRate;
    }
}
