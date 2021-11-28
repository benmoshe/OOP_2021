package jframe.password_cracker_gui;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import threads.password_cracker.CrackerRunnable;
import threads.password_cracker.Safe;

public class Main {
    public static void main(String[] args) {
        Safe safe = new Safe();
        CrackerRunnable cracker0 = new CrackerRunnable(safe, "0", "7zzzzz");
        CrackerRunnable cracker1 = new CrackerRunnable(safe, "7zzzzz", "dzzzzz");
        CrackerRunnable cracker2 = new CrackerRunnable(safe, "dzzzzz", "jzzzzz");
        CrackerRunnable cracker3 = new CrackerRunnable(safe, "jzzzzz", "pzzzzz");
        CrackerRunnable cracker4 = new CrackerRunnable(safe, "pzzzzz", "zzzzzz");
        CrackerRunnable[] crackers = { cracker0, cracker1, cracker2, cracker3, cracker4 };
        ExecutorService e = Executors.newFixedThreadPool(5);
        CrackerGui crg = new CrackerGui(safe, crackers);
        crg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        crg.setVisible(true);
        e.execute(cracker0);
        e.execute(cracker1);
        e.execute(cracker2);
        e.execute(cracker3);
        e.execute(cracker4);
        while (!CrackerRunnable.cracked) {
            crg.repaint();

        }

    }
}
