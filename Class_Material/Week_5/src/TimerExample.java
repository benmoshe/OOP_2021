/**
 * Timer Example from:
 * https://www.softwaretestinghelp.com/java/java-timer-tutorial/
 */

import java.util.Timer;
import java.util.TimerTask;

class Helper extends TimerTask {
    public static int i = 1;

    public void run() {
        System.out.println("This is called " + i++ + " time");
    }
}

public class TimerExample {
    public static void main(String[] args) {

        Timer timer = new Timer();

        // Helper class extends TimerTask
        TimerTask task = new Helper();

        /*
         *  Schedule() method calls for timer class.
         *  void schedule(TimerTask task, Date firstTime, long period)
         */

        timer.schedule(task, 2000, 500);

    }
}