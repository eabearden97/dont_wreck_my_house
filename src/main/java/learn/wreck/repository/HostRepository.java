package learn.wreck.repository;

import learn.wreck.models.Host;

import java.util.List;

public interface HostRepository {

    List<Host> findAll();

    Host findByEmail(String hostEmail);

    Host findByID(String hostID);
}
