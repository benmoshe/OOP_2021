package jframe.moving_cars;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gui extends JFrame {

    private class CarPanel extends JPanel {
        CarRunnable[] cars;

        public CarPanel(CarRunnable[] cars) {
            this.cars = cars;

        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (CarRunnable car : cars) {
                g.setColor(car.getColor());
                g.fillRect(car.getX(), this.getHeight() / 2, 100, 50);
            }
        }
    }

    public static final int WIDTH = 1080;
    public static final int HEIGHT = (int) (WIDTH / 1.6);
    CarRunnable[] cars;
    private CarPanel carPanel;

    public Gui(CarRunnable[] cars) {
        super();
        this.cars = cars;
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.carPanel = new CarPanel(this.cars);
        this.add(this.carPanel);
    }

}