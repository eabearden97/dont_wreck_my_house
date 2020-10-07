package learn.wreck.service;

import learn.wreck.models.Reservation;
import learn.wreck.repository.HostRepositoryDouble;
import learn.wreck.repository.ReservationRepositoryDouble;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {

    ReservationService service = new ReservationService(new ReservationRepositoryDouble(), new HostRepositoryDouble());

    @Test
    void shouldMakeReservation() {
    }

    @Test
    void shouldViewReservationByHost() {
    }

    @Test
    void editReservation() {
    }

    @Test
    void cancelReservation() {
    }
}