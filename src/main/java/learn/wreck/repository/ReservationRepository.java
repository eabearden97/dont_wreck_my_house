package learn.wreck.repository;

import learn.wreck.models.Reservation;

import java.util.List;

public interface ReservationRepository {

    Reservation makeReservation(Reservation reservation, String hostEmail, HostFileRepository hostRepository) throws DataException;

    List<Reservation> viewReservationsByHost(String email, HostFileRepository hostRepository);

    boolean editReservation(Reservation reservation);

    boolean cancelReservation(Reservation reservation);

}
