package learn.wreck.repository;

import learn.wreck.models.Host;

import java.util.List;

public interface HostRepository {

    List<Host> findAll();

    List<Host> findByEmail(String hostEmail);

    List<Host> findByID(String hostID);
}
