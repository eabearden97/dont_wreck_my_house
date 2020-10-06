package learn.wreck.repository;

import learn.wreck.models.Host;

import java.util.List;

public class HostFileRepository implements HostRepository{
    private final String HEADER="id,last_name,email,phone,address,city,state,postal_code,standard_rate,weekend_rate";
    private final String filePath;

    public HostFileRepository(String filePath) {
        this.filePath = filePath;
    }

    // TODO implement these methods and test
    @Override
    public List<Host> findAll() {
        return null;
    }

    @Override
    public List<Host> findByEmail(String hostEmail) {
        return null;
    }

    @Override
    public List<Host> findByID(String hostID) {
        return null;
    }
}
