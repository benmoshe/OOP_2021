package threads.moving_cars;
import java.lang.Thread;
public class CarRunnable implements Runnable{
    private int x = 0;
    private int speed = 0;
    public CarRunnable(int speed){
        this.speed = speed;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < Main.screenSize; i++) {
            this.x += this.speed;
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }

}