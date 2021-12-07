package observer;

import java.time.Duration;
import java.time.Instant;

public class Main {
    static Instant started;
    static Instant finished;

    public static void main(String[] args) {
        LoadingBar bar = new LoadingBar(10);
        bar.eventManager.subscribe("start", () -> {
            started = Instant.now();
        });
        bar.eventManager.subscribe("finish", () -> {
            finished = Instant.now();
            long time = Duration.between(started, finished).toMillis();
            System.out.println(time / 1000.0);
        });
        bar.eventManager.subscribe("finish", () -> {
            try {
                bar.start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        try {
            bar.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
