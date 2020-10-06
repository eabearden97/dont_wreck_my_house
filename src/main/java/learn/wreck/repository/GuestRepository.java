package learn.wreck.repository;

import learn.wreck.models.Guest;

import java.io.IOException;
import java.util.List;

public interface GuestRepository {

    List<Guest> findAll() throws IOException;

    Guest findByEmail(String guestEmail);

    Guest findByID(int guestID);
}
