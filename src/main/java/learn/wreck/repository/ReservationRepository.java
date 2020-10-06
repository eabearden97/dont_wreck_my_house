package learn.wreck.repository;

import learn.wreck.models.Reservation;

import java.util.List;

public interface ReservationRepository {

    Reservation makeReservation(Reservation reservation);

    List<Reservation> viewReservations(String hostEmail);

    boolean editReservation(Reservation reservation);

    boolean cancelReservation(Reservation reservation);

}
