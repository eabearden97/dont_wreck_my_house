package learn.wreck.repository;

import learn.wreck.models.Host;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HostRepositoryDouble implements HostRepository {

    List<Host> hostList = makeHostList();

    @Override
    public List<Host> findAll() {
        return hostList;
    }

    @Override
    public Host findByEmail(String hostEmail) {
        List<Host> hostList = findAll();
        for (Host host : hostList) {
            if (host.getEmailAddress().equalsIgnoreCase(hostEmail)) {
                return host;
            }
        }
        return null;
    }

    @Override
    public Host findByID(String hostID) {
        List<Host> hostList = findAll();
        for (Host host : hostList) {
            if (host.getHostID().equalsIgnoreCase(hostID)) {
                return host;
            }
        }
        return null;
    }

    private List<Host> makeHostList() {
        List<Host> hostList = new ArrayList<>();

        Host host = new Host();
        host.setHostID("d9faad1c-9fae-4afc-869c-d4fba7fa3446");
        host.setLastName("Haywood");
        host.setEmailAddress("ynhart@mac.edu");
        host.setPhoneNumber("923-126-4021");
        host.setStreetAddress("945 Lake Street Drive");
        host.setCity("Minneapolis");
        host.setState("MN");
        host.setPostalCode(55105);
        host.setStandardRate(new BigDecimal(100));
        host.setWeekendRate(new BigDecimal(150));

        hostList.add(host);

        return hostList;
    }
}
