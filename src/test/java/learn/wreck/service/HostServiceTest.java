package learn.wreck.service;

import learn.wreck.models.Host;
import learn.wreck.repository.HostRepository;
import learn.wreck.repository.HostRepositoryDouble;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HostServiceTest {

    HostService service = new HostService(new HostRepositoryDouble());

    @Test
    void shouldFindAll() {
        List<Host> hosts = service.findAll();

        assertNotNull(hosts);
        assertEquals(1, hosts.size());
    }

    @Test
    void shouldFindByEmail() {
        Host host = service.findByEmail("ynhart@mac.edu");

        assertNotNull(host);
        assertEquals("d9faad1c-9fae-4afc-869c-d4fba7fa3446", host.getHostID());
        assertEquals("Haywood", host.getLastName());
        assertEquals("923-126-4021", host.getPhoneNumber());
        assertEquals("945 Lake Street Drive", host.getStreetAddress());
        assertEquals("Minneapolis", host.getCity());
        assertEquals("MN", host.getState());
        assertEquals(55105, host.getPostalCode());
        assertEquals(new BigDecimal(100), host.getStandardRate());
        assertEquals(new BigDecimal(150), host.getWeekendRate());
    }

    @Test
    void shouldNotFindMissingEmail() {
        Host host = service.findByEmail("incorrectFake@no.com");

        assertNull(host);
    }

    @Test
    void shouldFindByID() {
        Host host = service.findByID("d9faad1c-9fae-4afc-869c-d4fba7fa3446");

        assertNotNull(host);
        assertEquals("ynhart@mac.edu", host.getEmailAddress());
        assertEquals("Haywood", host.getLastName());
        assertEquals("923-126-4021", host.getPhoneNumber());
        assertEquals("945 Lake Street Drive", host.getStreetAddress());
        assertEquals("Minneapolis", host.getCity());
        assertEquals("MN", host.getState());
        assertEquals(55105, host.getPostalCode());
        assertEquals(new BigDecimal(100), host.getStandardRate());
        assertEquals(new BigDecimal(150), host.getWeekendRate());
    }

    @Test
    void shouldNotFindMissingID() {
        Host host = service.findByID("fakeIDNumber");

        assertNull(host);
    }
}