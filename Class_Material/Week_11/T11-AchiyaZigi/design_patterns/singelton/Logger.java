package singelton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logger {
    private static Logger instance = new Logger();
    private Map<String, List<String>> logs = new HashMap<>();

    private Logger() {
        // nothing here!
    }

    public static Logger getInstance() {
        return instance;
    }

    public void log(String... msgs) {
        if (msgs.length > 0) {
            String key = msgs[0];
            if (!this.logs.containsKey(key)) {
                this.logs.put(msgs[0], new ArrayList<>());
            }
            String logline = "";
            for (int i = 1; i < msgs.length; i++) {
                logline += ' ' + msgs[i];
            }
            this.logs.get(key).add(logline);
            System.out.println('[' + key + ']' + logline);
        }
    }

    public void printLogs() {
        for (String key : logs.keySet()) {
            this.printLogs(key);
        }
    }

    public void printLogs(String key) {
        for (String logline : this.logs.get(key)) {
            System.out.println('[' + key + ']' + logline);
        }

    }

}