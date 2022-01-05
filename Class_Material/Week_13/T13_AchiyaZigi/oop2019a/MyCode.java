package oop2019a;

import java.util.Date;

public class MyCode implements car_code {

    private boolean o = false;
    private int errorCounter = 0;
    private Date lastError = null;
    private Date lastOpened = null;

    @Override
    public boolean open(String key) {
        if (canBeOpen()) {
            o = getCar().open(key);
            if (!o) {
                errorCounter++;
                if (errorCounter > 2) {
                    lastError = new Date();
                }
            } else {
                lastError = null;
                errorCounter = 0;
                lastOpened = new Date();
            }
            return o;
        }
        return false;
    }

    @Override
    public boolean isOpen() {
        if (getCar().isRunning()) {
            return true;
        }
        var lastOpenMili = lastOpened.getTime();
        var nowMili = new Date().getTime();
        if ((nowMili - lastOpenMili) / 1000 < 60) {
            // last run was less than 1 minute ago.
            return true;

        }
        return false;
    }

    @Override
    public boolean canBeOpen() {
        if (lastError == null) {
            return true;
        }
        var lastErrorMili = lastError.getTime();
        var nowMili = new Date().getTime();
        if ((nowMili - lastErrorMili) / 1000 < 15 * 60) {
            // last error was less than 15 minutes ago
            return false;
        }
        return true;
    }

    @Override
    public car getCar() {
        // TODO Auto-generated method stub
        return null;
    }

}
