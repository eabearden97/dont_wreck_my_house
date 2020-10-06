package learn.wreck.repository;

import learn.wreck.models.Guest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuestFileRepository implements GuestRepository{
    private final String filePath;

    public GuestFileRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Guest> findAll() {
        ArrayList<Guest> allGuests = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            reader.readLine();
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] fields = line.split(",",-1);
                if (fields.length == 6) {
                    Guest guest = deserialize(fields);
                    allGuests.add(guest);
                }
            }
        } catch (IOException ignored) {

        }
        return allGuests;
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

    private Guest deserialize(String[] fields) {
        Guest guest = new Guest();
        guest.setGuestID(Integer.parseInt(fields[0]));
        guest.setFirstName(fields[1]);
        guest.setLastName(fields[2]);
        guest.setEmailAddress(fields[3]);
        guest.setPhoneNumber(fields[4]);
        guest.setState(fields[5]);
        return guest;
    }

}
