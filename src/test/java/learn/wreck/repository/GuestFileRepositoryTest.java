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
    static final String TEST_DIR_PATH = "./test_data";

    GuestFileRepository repository = new GuestFileRepository(TEST_DIR_PATH);

    @BeforeEach
    void setUp() throws IOException {
        Path seedPath = Paths.get(SEED_FILE_PATH);
        Path testPath = Paths.get(TEST_FILE_PATH);
        Files.copy(seedPath,testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldFindAll() throws IOException {
        List<Guest> guests = repository.findAll();

        //assertNotNull(guests);
        assertEquals(1000,guests.size());
    }

    @Test
    void findByEmail() {
    }

    @Test
    void findByID() {
    }
}