package learn.wreck.service;

import learn.wreck.models.Guest;
import learn.wreck.repository.GuestRepositoryDouble;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GuestServiceTest {

    GuestService service = new GuestService(new GuestRepositoryDouble());

    @Test
    void shouldFindAll() {
        List<Guest> allGuests = service.findAll();

        assertNotNull(allGuests);
        assertEquals(1, allGuests.size());
    }

    @Test
    void shouldFindByEmail() {
        Guest guest = service.findByEmail("eabearden97@aol.com");

        assertNotNull(guest);
        assertEquals(5,guest.getGuestID());
        assertEquals("Alexander",guest.getFirstName());
        assertEquals("Bearden",guest.getLastName());
        assertEquals("eabearden97@aol.com", guest.getEmailAddress());
        assertEquals("555-832-0845", guest.getPhoneNumber());
        assertEquals("MO", guest.getState());
    }

    @Test
    void shouldNotFindMissingEmail() {
        Guest guest = service.findByEmail("fakeEmail@invalid.no");

        assertNull(guest);
    }

    @Test
    void shouldFindByID() {
        Guest guest = service.findByID(5);

        assertNotNull(guest);
        assertEquals("Alexander",guest.getFirstName());
        assertEquals("Bearden",guest.getLastName());
        assertEquals("eabearden97@aol.com", guest.getEmailAddress());
        assertEquals("555-832-0845", guest.getPhoneNumber());
        assertEquals("MO", guest.getState());
    }

    @Test
    void shouldNotFindMissingID() {
        Guest guest = service.findByID(100);

        assertNull(guest);
    }
}