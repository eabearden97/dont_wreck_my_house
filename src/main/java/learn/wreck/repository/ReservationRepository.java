package learn.wreck.repository;

import learn.wreck.models.Reservation;

import java.util.List;

public interface ReservationRepository {

    Reservation makeReservation(Reservation reservation, String hostEmail, HostRepository hostRepository) throws DataException;

    List<Reservation> viewReservationsByHost(String hostEmail, HostRepository hostRepository);

    boolean editReservation(Reservation reservation, String hostEmail, HostRepository hostRepository) throws DataException;

    boolean cancelReservation(Reservation reservation, String hostEmail, HostRepository hostRepository) throws DataException;

}
