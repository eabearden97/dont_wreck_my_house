package learn.wreck.repository;

import learn.wreck.models.Guest;

import java.util.ArrayList;
import java.util.List;

public class GuestRepositoryDouble implements GuestRepository {

    private final List<Guest> guestList = makeGuests();

    @Override
    public List<Guest> findAll() {
        return guestList;
    }

    @Override
    public Guest findByEmail(String guestEmail) {
        List<Guest> allGuests = findAll();
        for (Guest guest : allGuests) {
            if (guest.getEmailAddress().equalsIgnoreCase(guestEmail)) {
                return guest;
            }
        }
        return null;
    }

    @Override
    public Guest findByID(int guestID) {
        List<Guest> allGuests = findAll();
        for (Guest guest : allGuests) {
            if (guest.getGuestID() == guestID) {
                return guest;
            }
        }
        return null;
    }

    private List<Guest> makeGuests() {
        List<Guest> guestList = new ArrayList<>();

        Guest guest1 = new Guest();
        guest1.setGuestID(5);
        guest1.setFirstName("Alexander");
        guest1.setLastName("Bearden");
        guest1.setEmailAddress("eabearden97@aol.com");
        guest1.setPhoneNumber("555-832-0845");
        guest1.setState("MO");

        guestList.add(guest1);

        return guestList;
    }
}
