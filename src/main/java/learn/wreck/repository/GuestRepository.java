package learn.wreck.repository;

import learn.wreck.models.Guest;

import java.util.List;

public interface GuestRepository {

    List<Guest> findAll();

    Guest findByEmail(String guestEmail);

    Guest findByID(int guestID);
}
