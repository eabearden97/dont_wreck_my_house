package learn.wreck.repository;

import learn.wreck.models.Host;
import learn.wreck.models.Reservation;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationFileRepository implements ReservationRepository {
    private final String directory;
    private final String HEADER = "id,start_date,end_date,guest_id,total";

    public ReservationFileRepository(String directory) {
        this.directory = directory;
    }

    @Override
    //
    public Reservation makeReservation(Reservation newReservation, String hostEmail, HostRepository hostRepository) throws DataException {
        if (newReservation == null) {
            return null;
        }

        List<Reservation> existingReservations = viewReservationsByHost(hostEmail, hostRepository);
        String hostID = hostRepository.findByEmail(hostEmail).getHostID();

        int maxID = 0;
        for (Reservation reservation1 : existingReservations) {
            if (reservation1.getReservationID() > maxID) {
                maxID = reservation1.getReservationID();
            }
        }
        newReservation.setReservationID(maxID + 1);

        existingReservations.add(newReservation);
        writeAll(existingReservations, hostID);
        return newReservation;
    }

    @Override
    public List<Reservation> viewReservationsByHost(String hostEmail, HostRepository hostRepository) {
        Host host = hostRepository.findByEmail(hostEmail);
        String hostID = host.getHostID();

        ArrayList<Reservation> result = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(getFilePath(hostID)));
            reader.readLine();
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] fields = line.split(",",-1);
                if (fields.length == 5) {
                    Reservation reservation = deserialize(fields);
                    result.add(reservation);
                }
            }
        } catch (IOException ignored) {
        }
        return result;
    }

    @Override
    public boolean editReservation(Reservation reservation, String hostEmail, HostRepository hostRepository) throws DataException {
        if (reservation == null) {
            return false;
        }

        List<Reservation> reservations = viewReservationsByHost(hostEmail, hostRepository);
        for (int i=0; i < reservations.size(); i++) {
            if (reservations.get(i).getReservationID() == reservation.getReservationID()) {
                reservations.set(i, reservation);
                writeAll(reservations, hostRepository.findByEmail(hostEmail).getHostID());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean cancelReservation(Reservation reservation, String hostEmail, HostRepository hostRepository) throws DataException {
        if (reservation == null) {
            return false;
        }

        List<Reservation> reservations = viewReservationsByHost(hostEmail, hostRepository);
        for (int i=0; i < reservations.size(); i++) {
            if (reservations.get(i).getReservationID() == reservation.getReservationID()) {
                reservations.remove(reservations.get(i));
                writeAll(reservations, hostRepository.findByEmail(hostEmail).getHostID());
                return true;
            }
        }
        return false;
    }

    // =========================================================================
    // private methods

    private String getFilePath(String hostID) {
        String path = Paths.get(directory,hostID + ".csv").toString();
        return path;
    }

    private Reservation deserialize(String[] fields) {
        Reservation reservation = new Reservation();
        reservation.setReservationID(Integer.parseInt(fields[0]));
        reservation.setStartDate(LocalDate.parse(fields[1]));
        reservation.setEndDate(LocalDate.parse(fields[2]));
        reservation.setGuestID(Integer.parseInt(fields[3]));
        reservation.setTotalPrice(new BigDecimal(fields[4]));
        return reservation;
    }

    private String serialize(Reservation reservation) {
        String result = String.format("%s,%s,%s,%s,%s",
                reservation.getReservationID(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getGuestID(),
                reservation.getTotalPrice());
        return result;
    }

    private void writeAll(List<Reservation> reservations, String hostID) throws DataException {
        String filePath = getFilePath(hostID);
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println(HEADER);
            for (Reservation reservation : reservations) {
                String line = serialize(reservation);
                writer.println(line);
            }
        } catch (FileNotFoundException ex) {
            throw new DataException(ex);
        }
    }

}
