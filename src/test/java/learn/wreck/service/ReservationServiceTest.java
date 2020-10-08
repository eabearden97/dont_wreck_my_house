package learn.wreck.service;

import learn.wreck.models.Reservation;
import learn.wreck.repository.DataException;
import learn.wreck.repository.HostRepositoryDouble;
import learn.wreck.repository.ReservationRepositoryDouble;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {

    ReservationService service = new ReservationService(new ReservationRepositoryDouble(), new HostRepositoryDouble());
    String hostEmailAddress = "ynhart@mac.edu";

    @Test
    void shouldMakeReservation() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.of(2022,5,5));
        reservation.setEndDate(LocalDate.of(2022,5,10));
        reservation.setTotalPrice(new BigDecimal(469));
        reservation.setGuestID(6);

        service.makeReservation(reservation,hostEmailAddress);

        List<Reservation> reservations = service.viewReservationByHost(hostEmailAddress);

        assertNotNull(reservations);
        assertEquals(2, reservations.size());
    }

    @Test
    void shouldNotMakeNullReservation() throws DataException {
        service.makeReservation(null,hostEmailAddress);

        List<Reservation> reservations = service.viewReservationByHost(hostEmailAddress);

        assertNotNull(reservations);
        assertEquals(1, reservations.size());
    }

    @Test
    void shouldNotMakeReservationWithNullStartDate() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setStartDate(null);
        reservation.setEndDate(LocalDate.of(2022,5,10));
        reservation.setTotalPrice(new BigDecimal(469));
        reservation.setGuestID(6);

        service.makeReservation(reservation,hostEmailAddress);

        List<Reservation> reservations = service.viewReservationByHost(hostEmailAddress);

        assertNotNull(reservations);
        assertEquals(1, reservations.size());
    }

    @Test
    void shouldNotMakeReservationWithNullEndDate() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setStartDate(null);
        reservation.setEndDate(LocalDate.of(2022,5,10));
        reservation.setTotalPrice(new BigDecimal(469));
        reservation.setGuestID(6);

        service.makeReservation(reservation,hostEmailAddress);

        List<Reservation> reservations = service.viewReservationByHost(hostEmailAddress);

        assertNotNull(reservations);
        assertEquals(1, reservations.size());
    }

    @Test
    void shouldNotMakeReservationWithNullTotalPrice() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.of(2022,5,5));
        reservation.setEndDate(LocalDate.of(2022,5,10));
        reservation.setTotalPrice(null);
        reservation.setGuestID(6);

        service.makeReservation(reservation,hostEmailAddress);

        List<Reservation> reservations = service.viewReservationByHost(hostEmailAddress);

        assertNotNull(reservations);
        assertEquals(1, reservations.size());
    }

    @Test
    void shouldNotMakeReservationWithZeroGuestID() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.of(2022,5,5));
        reservation.setEndDate(LocalDate.of(2022,5,10));
        reservation.setTotalPrice(new BigDecimal(469));
        reservation.setGuestID(0);

        service.makeReservation(reservation,hostEmailAddress);

        List<Reservation> reservations = service.viewReservationByHost(hostEmailAddress);

        assertNotNull(reservations);
        assertEquals(1, reservations.size());
    }

    @Test
    void shouldNotMakeReservationWithEndBeforeStart() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.of(2022,5,10));
        reservation.setEndDate(LocalDate.of(2022,5,5));
        reservation.setTotalPrice(new BigDecimal(469));
        reservation.setGuestID(6);

        service.makeReservation(reservation,hostEmailAddress);

        List<Reservation> reservations = service.viewReservationByHost(hostEmailAddress);

        assertNotNull(reservations);
        assertEquals(1, reservations.size());
    }

    @Test
    void shouldNotMakeReservationWithEndEqualsStart() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.of(2022,5,5));
        reservation.setEndDate(LocalDate.of(2022,5,5));
        reservation.setTotalPrice(new BigDecimal(469));
        reservation.setGuestID(6);

        service.makeReservation(reservation,hostEmailAddress);

        List<Reservation> reservations = service.viewReservationByHost(hostEmailAddress);

        assertNotNull(reservations);
        assertEquals(1, reservations.size());
    }

    @Test
    void shouldNotMakeReservationWithStartInPast() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.of(2018,5,5));
        reservation.setEndDate(LocalDate.of(2022,5,10));
        reservation.setTotalPrice(new BigDecimal(469));
        reservation.setGuestID(6);

        service.makeReservation(reservation,hostEmailAddress);

        List<Reservation> reservations = service.viewReservationByHost(hostEmailAddress);

        assertNotNull(reservations);
        assertEquals(1, reservations.size());
    }

    @Test
    void shouldNotMakeReservationWithEndInPast() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.of(2022,5,5));
        reservation.setEndDate(LocalDate.of(2018,5,10));
        reservation.setTotalPrice(new BigDecimal(469));
        reservation.setGuestID(6);

        service.makeReservation(reservation,hostEmailAddress);

        List<Reservation> reservations = service.viewReservationByHost(hostEmailAddress);

        assertNotNull(reservations);
        assertEquals(1, reservations.size());
    }

    @Test
    void shouldNotMakeReservationWithPriceBelowZero() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.of(2022,5,5));
        reservation.setEndDate(LocalDate.of(2022,5,10));
        reservation.setTotalPrice(new BigDecimal(-100));
        reservation.setGuestID(6);

        service.makeReservation(reservation,hostEmailAddress);

        List<Reservation> reservations = service.viewReservationByHost(hostEmailAddress);

        assertNotNull(reservations);
        assertEquals(1, reservations.size());
    }

    @Test
    void shouldNotMakeReservationWithPriceEqualsZero() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.of(2022,5,5));
        reservation.setEndDate(LocalDate.of(2022,5,10));
        reservation.setTotalPrice(BigDecimal.ZERO);
        reservation.setGuestID(6);

        service.makeReservation(reservation,hostEmailAddress);

        List<Reservation> reservations = service.viewReservationByHost(hostEmailAddress);

        assertNotNull(reservations);
        assertEquals(1, reservations.size());
    }






    @Test
    void shouldViewReservationByHost() {
        List<Reservation> reservations = service.viewReservationByHost(hostEmailAddress);

        assertNotNull(reservations);
        assertEquals(1, reservations.size());
    }

    // TODO ask about this test, for the Double, I make a list and assume that it's for the email that's passed in
        // is this incorrect to do
//    @Test
//    void shouldNotViewReservationsForMissingHost() {
//        List<Reservation> reservations = service.viewReservationByHost("fakeEmail@invalid.no");
//
//        assertNotNull(reservations);
//        assertEquals(0, reservations.size());
//    }





    // TODO: should I be hard coding in the reservationID and guestID? We will learn what these values are in
        // the UI because the user must enter them... What about hostEmail/hostID?
    @Test
    void shouldEditReservation() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.of(2022,5,5));
        reservation.setEndDate(LocalDate.of(2022,5,10));
        reservation.setTotalPrice(new BigDecimal(469));
        reservation.setGuestID(6);
        reservation.setReservationID(10);

        service.editReservation(reservation, hostEmailAddress);
        List<Reservation> reservations = service.viewReservationByHost(hostEmailAddress);


        assertNotNull(reservation);
        assertEquals(1, reservations.size());

        assertEquals(reservation, reservations.get(0));
        assertEquals(LocalDate.of(2022,5,5), reservations.get(0).getStartDate());
        assertEquals(LocalDate.of(2022,5,10), reservations.get(0).getEndDate());
        assertEquals(new BigDecimal(469), reservations.get(0).getTotalPrice());
        assertEquals(6, reservations.get(0).getGuestID());
        assertEquals(10, reservations.get(0).getReservationID());
    }

    @Test
    void shouldNotEditNullReservation() throws DataException {
        service.editReservation(null, hostEmailAddress);
        List<Reservation> reservations = service.viewReservationByHost(hostEmailAddress);

        assertEquals(1, reservations.size());

        assertEquals(5, reservations.get(0).getGuestID());
        assertEquals(LocalDate.of(2025,8,8), reservations.get(0).getStartDate());
        assertEquals(LocalDate.of(2025,8,13), reservations.get(0).getEndDate());
        assertEquals(new BigDecimal(500), reservations.get(0).getTotalPrice());
        assertEquals(10, reservations.get(0).getReservationID());
    }

    @Test
    void shouldEditNullStartDate() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setStartDate(null);
        reservation.setEndDate(LocalDate.of(2025,8,28));
        reservation.setTotalPrice(new BigDecimal(469));
        reservation.setGuestID(6);
        reservation.setReservationID(10);

        service.editReservation(reservation, hostEmailAddress);
        List<Reservation> reservations = service.viewReservationByHost(hostEmailAddress);

        assertNotNull(reservation);
        assertEquals(1, reservations.size());

        assertEquals(reservation, reservations.get(0));
        assertEquals(LocalDate.of(2025,8,8), reservations.get(0).getStartDate());
        assertEquals(LocalDate.of(2025,8,28), reservations.get(0).getEndDate());
        assertEquals(new BigDecimal(469), reservations.get(0).getTotalPrice());
        assertEquals(6, reservations.get(0).getGuestID());
    }

    @Test
    void shouldEditNullEndDate() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.of(2025,8,8));
        reservation.setEndDate(null);
        reservation.setTotalPrice(new BigDecimal(469));
        reservation.setGuestID(6);
        reservation.setReservationID(10);

        service.editReservation(reservation, hostEmailAddress);
        List<Reservation> reservations = service.viewReservationByHost(hostEmailAddress);

        assertNotNull(reservation);
        assertEquals(1, reservations.size());

        assertEquals(reservation, reservations.get(0));
        assertEquals(LocalDate.of(2025,8,8), reservations.get(0).getStartDate());
        assertEquals(LocalDate.of(2025,8,13), reservations.get(0).getEndDate());
        assertEquals(new BigDecimal(469), reservations.get(0).getTotalPrice());
        assertEquals(6, reservations.get(0).getGuestID());
    }

    @Test
    void shouldEditNullTotalPrice() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.of(2025,8,8));
        reservation.setEndDate(LocalDate.of(2025,8,13));
        reservation.setTotalPrice(null);
        reservation.setGuestID(6);
        reservation.setReservationID(10);

        service.editReservation(reservation, hostEmailAddress);
        List<Reservation> reservations = service.viewReservationByHost(hostEmailAddress);

        assertNotNull(reservation);
        assertEquals(1, reservations.size());

        assertEquals(reservation, reservations.get(0));
        assertEquals(LocalDate.of(2025,8,8), reservations.get(0).getStartDate());
        assertEquals(LocalDate.of(2025,8,13), reservations.get(0).getEndDate());
        assertEquals(new BigDecimal(500), reservations.get(0).getTotalPrice());
        assertEquals(6, reservations.get(0).getGuestID());
    }

    @Test
    void shouldEditZeroGuestID() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.of(2025,8,8));
        reservation.setEndDate(LocalDate.of(2025,8,13));
        reservation.setTotalPrice(new BigDecimal(469));
        reservation.setGuestID(0);
        reservation.setReservationID(10);

        service.editReservation(reservation, hostEmailAddress);
        List<Reservation> reservations = service.viewReservationByHost(hostEmailAddress);

        assertNotNull(reservation);
        assertEquals(1, reservations.size());

        assertEquals(reservation, reservations.get(0));
        assertEquals(LocalDate.of(2025,8,8), reservations.get(0).getStartDate());
        assertEquals(LocalDate.of(2025,8,13), reservations.get(0).getEndDate());
        assertEquals(new BigDecimal(469), reservations.get(0).getTotalPrice());
        assertEquals(5, reservations.get(0).getGuestID());
    }





    @Test
    void shouldCancelReservation() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setReservationID(10);

        List<Reservation> reservations = service.viewReservationByHost(hostEmailAddress);

        assertEquals(1, reservations.size());

        service.cancelReservation(reservation, hostEmailAddress);

        assertEquals(0, reservations.size());
    }

    @Test
    void shouldNotCancelReservationWithMissingReservationID() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setReservationID(100);

        List<Reservation> reservations = service.viewReservationByHost(hostEmailAddress);

        assertEquals(1, reservations.size());

        service.cancelReservation(reservation, hostEmailAddress);

        assertEquals(1, reservations.size());
    }
}