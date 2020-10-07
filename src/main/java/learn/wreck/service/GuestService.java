package learn.wreck.service;

import learn.wreck.models.Guest;
import learn.wreck.repository.GuestRepository;

import java.util.List;

public class GuestService {

    private final GuestRepository repository;

    public GuestService(GuestRepository repository) {
        this.repository=repository;
    }

    public List<Guest> findAll() {
        return repository.findAll();
    }

    public Guest findByEmail(String guestEmail) {
        List<Guest> allGuests = repository.findAll();
        for (Guest guest : allGuests) {
            if (guest.getEmailAddress().equalsIgnoreCase(guestEmail)) {
                return guest;
            }
        }
        return null;
    }

    public Guest findByID(int guestID) {
        List<Guest> allGuests = repository.findAll();
        for (Guest guest : allGuests) {
            if (guest.getGuestID() == guestID) {
                return guest;
            }
        }
        return null;
    }

}
