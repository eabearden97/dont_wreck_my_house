package learn.wreck.service;

import java.util.ArrayList;
import java.util.List;

public class Response {

    ArrayList<String> messages = new ArrayList<>();

    public boolean isSuccess() {
        return (messages.size() == 0);
    }

    public List<String> getMessages() {
        return new ArrayList<>(messages);
    }

    public void addErrorMessage(String description) {
        messages.add(description);
    }

}
