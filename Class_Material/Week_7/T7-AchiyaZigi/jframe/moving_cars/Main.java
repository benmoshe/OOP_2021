package jframe.moving_cars;

import java.awt.Color;

public class Main {
    public static Gui gui;

    public static void main(String args[]) {
        CarRunnable carToRight = new CarRunnable(4, 100, 1);
        CarRunnable carToLeft = new CarRunnable(3, 800, -1);

        carToRight.setColor(Color.BLUE);
        carToLeft.setColor(Color.RED);

        Thread toRight = new Thread(carToRight);
        Thread toLeft = new Thread(carToLeft);

        CarRunnable[] cars = { carToRight, carToLeft };
        gui = new Gui(cars);
        toRight.start();
        toLeft.start();
        gui.setVisible(true);

        boolean collided = false;
        // monitoring:
        while (toLeft.isAlive() || toRight.isAlive()) {

            // collision:
            if (Math.abs(carToLeft.getX() - carToRight.getX()) < 100 && !collided) {
                carToLeft.setDir(carToLeft.getDir() * -1);
                carToRight.setDir(carToRight.getDir() * -1);
                collided = true;
            } else {
                collided = false;
            }

            Main.gui.repaint();

            // 60 frames per second
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
