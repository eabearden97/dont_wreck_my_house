package learn.wreck.repository;

import learn.wreck.models.Guest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GuestFileRepositoryTest {
    static final String TEST_FILE_PATH = "./test_data/guest-test.csv";
    static final String SEED_FILE_PATH = "./test_data/guest-seed.csv";

    GuestFileRepository repository = new GuestFileRepository(TEST_FILE_PATH);

    @BeforeEach
    void setUp() throws IOException {
        Path seedPath = Paths.get(SEED_FILE_PATH);
        Path testPath = Paths.get(TEST_FILE_PATH);
        Files.copy(seedPath,testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldFindAll() {
        List<Guest> guests = repository.findAll();

        assertNotNull(guests);
        assertEquals(1000,guests.size());
    }

    @Test
    void shouldFindByEmail() {
        Guest guest = repository.findByEmail("slomas0@mediafire.com");

        assertNotNull(guest);
        assertEquals(1, guest.getGuestID());
        assertEquals("Sullivan", guest.getFirstName());
        assertEquals("Lomas", guest.getLastName());
        assertEquals("slomas0@mediafire.com", guest.getEmailAddress());
        assertEquals("(702) 7768761", guest.getPhoneNumber());
        assertEquals("NV", guest.getState());
    }

    @Test
    void shouldNotFindMissingEmail() {
        Guest guest = repository.findByEmail("invalidEmail@wrong.com");

        assertNull(guest);
    }

    @Test
    void shouldFindByID() {
        Guest guest = repository.findByID(2);

        assertNotNull(guest);
        assertEquals("Olympie", guest.getFirstName());
        assertEquals("Gecks", guest.getLastName());
        assertEquals("ogecks1@dagondesign.com", guest.getEmailAddress());
        assertEquals("(202) 2528316", guest.getPhoneNumber());
        assertEquals("DC", guest.getState());
    }

    @Test
    void shouldNotFindMissingID() {
        Guest guest1 = repository.findByID(252525);
        Guest guest2 = repository.findByID(-10);

        assertNull(guest1);
        assertNull(guest2);
    }
}