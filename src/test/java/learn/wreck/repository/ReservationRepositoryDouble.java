package learn.wreck.repository;

import learn.wreck.models.Reservation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepositoryDouble implements ReservationRepository {

    private final List<Reservation> reservations = createReservationList();

    @Override
    public Reservation makeReservation(Reservation reservation, String hostEmail, HostRepository hostRepository) throws DataException {
        reservations.add(reservation);
        return reservation;
    }

    @Override
    public List<Reservation> viewReservationsByHost(String hostEmail, HostRepository hostRepository) {
        return reservations;
    }

    @Override
    public boolean editReservation(Reservation reservation, String hostEmail, HostRepository hostRepository) throws DataException {
        for (int i=0; i<reservations.size(); i++) {
            if (reservation.getReservationID() == reservations.get(i).getReservationID()) {
                reservations.set(i,reservation);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean cancelReservation(Reservation reservation, String hostEmail, HostRepository hostRepository) throws DataException {
        for (int i=0; i<reservations.size(); i++) {
            if (reservation.getReservationID() == reservations.get(i).getReservationID()) {
                reservations.remove(i);
                return true;
            }
        }
        return false;
    }

    private List<Reservation> createReservationList() {
        List<Reservation> reservations = new ArrayList<>();

        Reservation reservation = new Reservation();
        reservation.setGuestID(5);
        reservation.setStartDate(LocalDate.of(2025,8,8));
        reservation.setEndDate(LocalDate.of(2025,8,13));
        reservation.setTotalPrice(new BigDecimal(500));
        reservation.setReservationID(10);

        reservations.add(reservation);

        return reservations;
    }
}
