package threads.password_cracker;

import java.util.concurrent.*;
import java.time.Duration;
import java.time.Instant;

public class Main {

    public static void main(String[] args) {

        Safe safe = new Safe();
        Thread cracker0 = new Thread(new CrackerRunnable(safe, "0", "azzz"));
        Thread cracker1 = new Thread(new CrackerRunnable(safe, "azzz", "9zzzz"));
        Thread cracker2 = new Thread(new CrackerRunnable(safe, "9zzzz", "zzzzz"));
        Thread cracker3 = new Thread(new CrackerRunnable(safe, "zzzzz", "9zzzzz"));
        Thread cracker4 = new Thread(new CrackerRunnable(safe, "9zzzzz", "fzzzzz"));
        Thread cracker5 = new Thread(new CrackerRunnable(safe, "fzzzzz", "zzzzzz"));
        Instant start = Instant.now();
        cracker0.start();
        cracker1.start();
        cracker2.start();
        cracker3.start();
        cracker4.start();
        cracker5.start();

        try {
            cracker0.join();
            cracker1.join();
            cracker2.join();
            cracker3.join();
            cracker4.join();
            cracker5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Instant finish = Instant.now();
        long time = Duration.between(start, finish).toSeconds();
        System.out.println("it took: " + time + " seconds");

        CrackerRunnable.cracked = false;

        Thread cracker6 = new Thread(new CrackerRunnable(safe, "0", "zzzzzz"));

        start = Instant.now();
        cracker6.start();
        try {
            cracker6.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finish = Instant.now();
        time = Duration.between(start, finish).toSeconds();
        System.out.println("it took: " + time + " seconds");

        CrackerRunnable.cracked = false;

        ExecutorService e = Executors.newFixedThreadPool(2);
        start = Instant.now();
        e.execute(new CrackerRunnable(safe, "0", "azzz"));
        e.execute(new CrackerRunnable(safe, "azzz", "9zzzz"));
        e.execute(new CrackerRunnable(safe, "9zzzz", "zzzzz"));
        e.execute(new CrackerRunnable(safe, "zzzzz", "9zzzzz"));
        e.execute(new CrackerRunnable(safe, "9zzzzz", "fzzzzz"));
        e.execute(new CrackerRunnable(safe, "fzzzzz", "zzzzzz"));

        e.shutdown();
        try {
            if (!e.awaitTermination(60, TimeUnit.SECONDS)) {
                e.shutdownNow();
            }
        } catch (InterruptedException ex) {
            e.shutdownNow();
            Thread.currentThread().interrupt();
        }
        finish = Instant.now();
        time = Duration.between(start, finish).toSeconds();
        System.out.println("it took: " + time + " seconds");
    }
}
