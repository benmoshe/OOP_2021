package threads.basic;

import java.time.Duration;
import java.time.Instant;

public class IncDummy implements IncInterface {

    public int x = 0;

    public synchronized void inc() {
        this.x++;
    }

    // ----------------------------------
    public static void run(IncInterface inc) {

        for (int i = 0; i < 10000; i++) {
            inc.inc();
        }
    }

    public static void main(String[] args) {

        IncDummy inc = new IncDummy();

        Thread t0 = new Thread(() -> {
            run(inc);
        });

        Thread t1 = new Thread(() -> {
            run(inc);
        });

        t0.setName("0");
        t1.setName("1");

        Instant start = Instant.now();

        t0.start();
        t1.start();

        try {
            t0.join();
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Instant finish = Instant.now();
        long time = Duration.between(start, finish).toNanos();

        System.out.println(inc.x);
        System.out.println("Finished in: " + time / 1000 + " us'");
        // ----------------------------------

        IncSmart incS = new IncSmart();
        t0 = new Thread(() -> {
            run(incS);
        });
        t1 = new Thread(() -> {
            run(incS);
        });

        start = Instant.now();
        t0.start();
        t1.start();

        try {
            t0.join();
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finish = Instant.now();
        time = Duration.between(start, finish).toNanos();
        System.out.println(incS.x);
        System.out.println("Finished in: " + time / 1000 + " us'");

    }
}
