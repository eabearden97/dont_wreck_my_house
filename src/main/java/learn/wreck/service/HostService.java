package learn.wreck.service;

import learn.wreck.models.Host;
import learn.wreck.repository.HostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostService {

    private final HostRepository repository;

    public HostService(HostRepository repository) {
        this.repository = repository;
    }

    public List<Host> findAll() {
        return repository.findAll();
    }

    // TODO ask question about why I wouldn't use findByEmail method from repository
    public Host findByEmail(String hostEmail) {
        List<Host> allHosts = repository.findAll();
        for (Host host : allHosts) {
            if (host.getEmailAddress().equalsIgnoreCase(hostEmail)) {
                return host;
            }
        }
        return null;
    }

    public Host findByID(String hostID) {
        List<Host> allHosts = repository.findAll();
        for (Host host : allHosts) {
            if (host.getHostID().equalsIgnoreCase(hostID)) {
                return host;
            }
        }
        return null;
    }

}
