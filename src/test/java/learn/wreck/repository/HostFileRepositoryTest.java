package learn.wreck.repository;

import learn.wreck.models.Host;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HostFileRepositoryTest {
    private final String TEST_FILE_PATH="./test_data/host-test.csv";
    private final String SEED_FILE_PATH="./test_data/host-seed.csv";

    HostFileRepository repository = new HostFileRepository(TEST_FILE_PATH);

    @BeforeEach
    void setUp() throws IOException {
        Path seedPath = Paths.get(SEED_FILE_PATH);
        Path testPath = Paths.get(TEST_FILE_PATH);
        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void findAll() {
        List<Host> allHosts = repository.findAll();

        assertNotNull(allHosts);
        assertEquals(1000,allHosts.size());
    }

    @Test
    void shouldFindByEmail() {
        Host host = repository.findByEmail("mfader2@amazon.co.jp");

        assertNotNull(host);
        assertEquals("b4f38829-c663-48fc-8bf3-7fca47a7ae70", host.getHostID());
        assertEquals("Fader", host.getLastName());
        assertEquals("(501) 2490895", host.getPhoneNumber());
        assertEquals("99208 Morning Parkway", host.getStreetAddress());
        assertEquals("North Little Rock", host.getCity());
        assertEquals("AR", host.getState());
        assertEquals(72118, host.getPostalCode());
        assertEquals(new BigDecimal(451), host.getStandardRate());
        assertEquals(new BigDecimal(563.75), host.getWeekendRate());
    }

    @Test
    void shouldNotFindInvalidEmail() {
        Host host = repository.findByEmail("InvalidEmail@incorrect.org");

        assertNull(host);
    }

    @Test
    void findByID() {
        Host host = repository.findByID("b4f38829-c663-48fc-8bf3-7fca47a7ae70");

        assertNotNull(host);
        assertEquals("Fader", host.getLastName());
        assertEquals("mfader2@amazon.co.jp",host.getEmailAddress());
        assertEquals("(501) 2490895", host.getPhoneNumber());
        assertEquals("99208 Morning Parkway", host.getStreetAddress());
        assertEquals("North Little Rock", host.getCity());
        assertEquals("AR", host.getState());
        assertEquals(72118, host.getPostalCode());
        assertEquals(new BigDecimal(451), host.getStandardRate());
        assertEquals(new BigDecimal(563.75), host.getWeekendRate());
    }

    @Test
    void shouldNotFindMissingID() {
        Host host = repository.findByID("o4f38829-bje7-48fc-8bf3-7fca47a7ae70");

        assertNull(host);
    }
}