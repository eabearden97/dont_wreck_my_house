package learn.wreck.repository;

import learn.wreck.models.Host;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HostFileRepository implements HostRepository{
    private final String filePath;

    public HostFileRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Host> findAll() {
        ArrayList<Host> result = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            reader.readLine();
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] fields = line.split(",",-1);
                if (fields.length==10) {
                    Host host = deserialize(fields);
                    result.add(host);
                }
            }
        } catch (IOException ignored) {
        }
        return result;
    }

    @Override
    public Host findByEmail(String hostEmail) {
        List<Host> allHosts = findAll();
        for (Host host : allHosts) {
            if (host.getEmailAddress().equalsIgnoreCase(hostEmail)) {
                return host;
            }
        }
        return null;
    }

    @Override
    public Host findByID(String hostID) {
        List<Host> allHosts = findAll();
        for (Host host : allHosts) {
            if (host.getHostID().equalsIgnoreCase(hostID)) {
                return host;
            }
        }
        return null;
    }

    private Host deserialize(String[] fields) {
        Host host = new Host();
        host.setHostID(fields[0]);
        host.setLastName(fields[1]);
        host.setEmailAddress(fields[2]);
        host.setPhoneNumber(fields[3]);
        host.setStreetAddress(fields[4]);
        host.setCity(fields[5]);
        host.setState(fields[6]);
        host.setPostalCode(Integer.parseInt(fields[7]));
        host.setStandardRate(new BigDecimal(fields[8]));
        host.setWeekendRate(new BigDecimal(fields[9]));
        return host;
    }

}
