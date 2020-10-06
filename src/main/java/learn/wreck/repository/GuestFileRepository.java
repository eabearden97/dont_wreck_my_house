package learn.wreck.repository;

import learn.wreck.models.Guest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuestFileRepository implements GuestRepository{
    private final String HEADER="guest_id,first_name,last_name,email,phone,state";
    private final String filePath;

    public GuestFileRepository(String filePath) {
        this.filePath = filePath;
    }

    // TODO implement these methods and test them
    @Override
    public List<Guest> findAll() {
        ArrayList<Guest> allGuests = null;
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
        } catch (IOException e) {

        }
        return allGuests;
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

    @Override
    public List<Guest> findByEmail(String guestEmail) {
        return null;
    }

    @Override
    public List<Guest> findByID(int guestID) {
        return null;
    }
}
