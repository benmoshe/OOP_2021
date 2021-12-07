package observer;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;

public class EventManager {
    private HashMap<String, List<MethodInreface>> events = new HashMap<>();

    public void post(String event) {
        List<MethodInreface> toExecute = this.events.get(event);
        if (toExecute == null) {
            return;
        }
        for (MethodInreface method : toExecute) {
            method.execute();
        }
    }

    public void subscribe(String event, MethodInreface method) {
        if (!this.events.containsKey(event)) {
            this.events.put(event, new ArrayList<>());
        }
        this.events.get(event).add(method);
    }

}