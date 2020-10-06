package learn.wreck.repository;

import learn.wreck.models.Reservation;

import java.util.List;

public class ReservationFileRepository implements ReservationRepository {
    private final String HEADER="id,start_date,end_date,guest_id,total";
    private final String filePath;

    public ReservationFileRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Reservation makeReservation(Reservation reservation) {
        return null;
    }

    @Override
    public List<Reservation> viewReservations(String hostEmail) {
        return null;
    }

    @Override
    public boolean editReservation(Reservation reservation) {
        return false;
    }

    @Override
    public boolean cancelReservation(Reservation reservation) {
        return false;
    }
}
