package learn.wreck.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Reservation {
    private int reservationID;
    private LocalDate startDate;
    private LocalDate endDate;
    private int guestID;
    private BigDecimal totalPrice;

    public Reservation(int reservationID, LocalDate startDate, LocalDate endDate, int guestID, BigDecimal totalPrice) {
        this.reservationID = reservationID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.guestID = guestID;
        this.totalPrice = totalPrice;
    }

    public Reservation() {
    }

    public int getReservationID() {
        return reservationID;
    }
    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public int getGuestID() {
        return guestID;
    }
    public void setGuestID(int guestID) {
        this.guestID = guestID;
    }
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
