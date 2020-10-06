package learn.wreck.repository;

import learn.wreck.models.Guest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface GuestRepository {

    List<Guest> findAll() throws IOException;

    List<Guest> findByEmail(String guestEmail);

    List<Guest> findByID(int guestID);
}
