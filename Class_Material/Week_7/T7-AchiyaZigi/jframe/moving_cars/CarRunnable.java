package jframe.moving_cars;
import java.awt.Color;
import java.lang.Thread;

public class CarRunnable implements Runnable {
    private int x = 0;
    private int speed = 0;
    private int dir = 0;
    private Color color;

    public CarRunnable(int speed, int startingX, int dir) {
        this.speed = speed;
        this.x = startingX;
        this.dir = dir;
        this.color = new Color(50,50,50);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setColor(Color c){
        this.color = c;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < Gui.WIDTH; i++) {
            this.x += this.speed * this.dir;

            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public Color getColor() {
        return this.color;
    }

    public int getDir() {
        return this.dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

}