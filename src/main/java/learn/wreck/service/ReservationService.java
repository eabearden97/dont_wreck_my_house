package learn.wreck.service;

import learn.wreck.models.Reservation;
import learn.wreck.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final HostRepository hostRepository;

    public ReservationService(ReservationRepository reservationRepository, HostRepository hostRepository) {
        this.reservationRepository = reservationRepository;
        this.hostRepository = hostRepository;
    }

    public Result<Reservation> makeReservation(Reservation reservation, String hostEmail) throws DataException {
        Result<Reservation> result = validate(reservation, hostEmail);
        if (!result.isSuccess()) {
            return result;
        }
        return result;
    }

    public Result<Reservation> setReservation(Reservation reservation, String hostEmail) throws DataException {
        Result<Reservation> result = new Result<>();
        if (reservation != null) {
            result.setPayload(reservationRepository.makeReservation(reservation, hostEmail, hostRepository));
        }
        return result;
    }

    public List<Reservation> viewReservationByHost(String hostEmail) {
        List<Reservation> reservations = reservationRepository.viewReservationsByHost(hostEmail, hostRepository);
        return reservations;
    }

    public Result<Reservation> editReservation(Reservation reservation1, String hostEmail) throws DataException {
        Result<Reservation> result = new Result<>();

        if (reservation1 == null) {
            result.addErrorMessage("Cannot edit with a null reservation.");
            return result;
        }

        // TODO fillInNullFields should be taken away because I deal with filling in nulls in UI layer
            // at this point, there shouldn't be any nulls
        Reservation reservation = fillInNullReservationFields(reservation1, hostEmail);
        result = validate(reservation, hostEmail);
        if (!result.isSuccess()) {
            return result;
        }

        List<Reservation> existingReservations = reservationRepository.viewReservationsByHost(hostEmail, hostRepository);

        boolean found = false;
        for (Reservation existingReservation : existingReservations) {
            if (existingReservation.getReservationID() == reservation.getReservationID()) {
                found = true;
            }
        }

        if (!found) {
            result.addErrorMessage("Reservation ID " + reservation.getReservationID() + " does not already exist.");
            return result;
        }

        boolean success = reservationRepository.editReservation(reservation,hostEmail, hostRepository);
        if (!success) {
            result.addErrorMessage("Could not find Reservation ID " + reservation.getReservationID());
        }

        return result;
    }

    public boolean cancelReservation(Reservation reservation, String hostEmail) throws DataException {
        List<Reservation> existingReservations = reservationRepository.viewReservationsByHost(hostEmail, hostRepository);
        for (Reservation existingReservation : existingReservations) {
            if (existingReservation.getReservationID() == reservation.getReservationID()) {
                reservationRepository.cancelReservation(reservation, hostEmail, hostRepository);
                return true;
            }
        }

        return false;
    }

    // private methods

    private Result<Reservation> validate(Reservation reservation, String hostEmail) {
        Result<Reservation> result = validateNulls(reservation);
        if (!result.isSuccess()) {
            return result;
        }

        result = validateFields(reservation, result);
        if (!result.isSuccess()) {
            return result;
        }

        // TODO: add this to validateFields
        result = validateOverlappingDates(reservation, hostEmail);
        if (!result.isSuccess()) {
            return result;
        }

        return result;
    }

    private Result<Reservation> validateNulls(Reservation reservation) {
        Result<Reservation> result = new Result<>();

        if (reservation == (null)) {
            result.addErrorMessage("Cannot make a null reservation.");
            return result;
        }
        if (reservation.getStartDate() == null) {
            result.addErrorMessage("Cannot make a reservation without a start date.");
        }
        if (reservation.getEndDate() == null) {
            result.addErrorMessage("Cannot make a reservation without an end date.");
        }
        if (reservation.getTotalPrice() == null) {
            result.addErrorMessage("Cannot make a reservation without a price.");
        }
        if (reservation.getGuestID() == 0) {
            result.addErrorMessage("Cannot make a reservation without a guest ID.");
        }

        return result;
    }

    private Result<Reservation> validateFields(Reservation reservation, Result result) {
        if (reservation.getStartDate().isBefore(LocalDate.now())) {
            result.addErrorMessage("Start date must be in the future.");
        }
        if (reservation.getEndDate().isBefore(LocalDate.now())) {
            result.addErrorMessage("End date must be in the future.");
        }
        if (reservation.getStartDate().isAfter(reservation.getEndDate())) {
            result.addErrorMessage("Start date must come before the end date.");
        }
        if (reservation.getStartDate().isEqual(reservation.getEndDate())) {
            result.addErrorMessage("Must make a reservation for at least one night.");
        }
        if (reservation.getTotalPrice().compareTo(BigDecimal.ZERO) < 0) {
            result.addErrorMessage("Total price must be at least $0.00");
        }

        return result;
    }

    // TODO: should this be in the domain layer or the data layer?
    private Reservation fillInNullReservationFields(Reservation reservation, String hostEmail) {
        List<Reservation> existingReservations = reservationRepository.viewReservationsByHost(hostEmail, hostRepository);
        Reservation reservationToEdit = null;
        for (Reservation existingReservation : existingReservations) {
            if (existingReservation.getReservationID() == reservation.getReservationID()) {
                reservationToEdit = existingReservation;
            }
        }

        if (reservation.getStartDate() == null) {
            reservation.setStartDate(reservationToEdit.getStartDate());
        }
        if (reservation.getEndDate() == null) {
            reservation.setEndDate(reservationToEdit.getEndDate());
        }
        if (reservation.getTotalPrice() == null) {
            reservation.setTotalPrice(reservationToEdit.getTotalPrice());
        }
        if (reservation.getGuestID() == 0) {
            reservation.setGuestID(reservationToEdit.getGuestID());
        }

        return reservation;
    }

    public Result<Reservation> validateOverlappingDates(Reservation reservation, String hostEmail) {
        Result<Reservation> result = new Result<>();
        List<Reservation> existingReservations = reservationRepository.viewReservationsByHost(hostEmail, hostRepository);
        for (Reservation existingReservation : existingReservations) {
            if (existingReservation.getReservationID() != reservation.getReservationID()) {
                if (reservation.getStartDate().isBefore(existingReservation.getStartDate()) &&
                        reservation.getEndDate().isBefore(existingReservation.getStartDate())) {
                } else if (reservation.getStartDate().isBefore(existingReservation.getStartDate()) &&
                        reservation.getEndDate().isEqual(existingReservation.getStartDate())){
                } else if (reservation.getStartDate().isEqual(existingReservation.getEndDate()) &&
                        reservation.getEndDate().isAfter(existingReservation.getEndDate())){
                } else if (reservation.getStartDate().isAfter(existingReservation.getEndDate()) &&
                        reservation.getEndDate().isAfter(existingReservation.getEndDate())) {
                } else {
                    result.addErrorMessage("Cannot make reservation with overlapping dates.");
                    return result;
                }
            }
        }
        return result;
    }

}
