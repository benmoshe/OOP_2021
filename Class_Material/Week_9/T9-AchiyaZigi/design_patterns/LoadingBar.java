import java.util.Random;

public class LoadingBar {
    public EventManager eventManager;
    int iterations;

    public LoadingBar(int iterations) {
        this.iterations = iterations;
        this.eventManager = new EventManager();
    }

    public void start() throws InterruptedException {
        Thread.sleep(new Random().nextInt(10) * 1000);
        this.eventManager.post("start");
        for (int i = 0; i < this.iterations; i++) {
            System.out.print('-');
            Thread.sleep(500);
        }
        System.out.println();
        this.eventManager.post("finish");
    }
}
