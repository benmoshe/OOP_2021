package jframe.basic;

import java.awt.Color;

import javax.swing.JFrame;

public class Main {
    static final int WIDTH = 1080;
    static final int HEIGHT = (int) (WIDTH / 1.6);

    public static void main(String[] args) {

        JFrame mainFrame = new JFrame();
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.setVisible(true);
        while (true) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mainFrame.getContentPane().setBackground(Color.BLACK);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mainFrame.getContentPane().setBackground(Color.GREEN);
        }
    }
}
