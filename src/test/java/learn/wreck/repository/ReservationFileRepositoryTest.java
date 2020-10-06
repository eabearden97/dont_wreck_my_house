package learn.wreck.repository;

import learn.wreck.models.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.FileSystemUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationFileRepositoryTest {
    private final String TEST_DIR_PATH = "./test_data/reservations-test";
    private final String SEED_DIR_PATH = "./test_data/reservations-seed";
    private final String HOST_TEST_PATH = "./test_data/host-test.csv";
    private final String HOST_SEED_PATH = "./test_data/host-seed.csv";

    ReservationFileRepository repository = new ReservationFileRepository(TEST_DIR_PATH);
    HostFileRepository hostRepository = new HostFileRepository(HOST_TEST_PATH);

    String hostID = "2e25f6f7-3ef0-4f38-8a1a-2b5eea81409c";
    String hostEmail = hostRepository.findByID(hostID).getEmailAddress();

    @BeforeEach
    void setUp() throws IOException {
        Path testPath = Paths.get(TEST_DIR_PATH);
        Path seedPath = Paths.get(SEED_DIR_PATH);
        FileSystemUtils.copyRecursively(seedPath, testPath);
        Path hostTestPath = Paths.get(HOST_TEST_PATH);
        Path hostSeedPath = Paths.get(HOST_SEED_PATH);
        Files.copy(hostSeedPath, hostTestPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldMakeReservation() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.of(2022,8,8));
        reservation.setEndDate(LocalDate.of(2022,8,13));
        reservation.setGuestID(1);
        reservation.setTotalPrice(new BigDecimal(500));

        assertEquals(13, repository.viewReservationsByHost(hostEmail, hostRepository).size());

        Reservation newReservation = repository.makeReservation(reservation,hostEmail,hostRepository);

        assertNotNull(newReservation);
        assertEquals(14, repository.viewReservationsByHost(hostEmail, hostRepository).size());
    }

    @Test
    void shouldNotMakeNullReservation() throws DataException {
        assertEquals(13, repository.viewReservationsByHost(hostEmail, hostRepository).size());

        Reservation newReservation = repository.makeReservation(null,hostEmail,hostRepository);

        assertNull(newReservation);
        assertEquals(13, repository.viewReservationsByHost(hostEmail, hostRepository).size());
    }

    @Test
    void viewReservations() {
        List<Reservation> reservations = repository.viewReservationsByHost(hostEmail,hostRepository);

        assertNotNull(reservations);
        assertEquals(13, reservations.size());
    }

    @Test
    void editReservation() {
    }

    @Test
    void cancelReservation() {
    }
}