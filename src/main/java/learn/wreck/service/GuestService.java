package learn.wreck.service;

import learn.wreck.models.Guest;
import learn.wreck.repository.GuestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        Result<Guest> result = new Result<>();
        result.addErrorMessage("This guest does not exist");
        System.out.println(result.getMessages());
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
