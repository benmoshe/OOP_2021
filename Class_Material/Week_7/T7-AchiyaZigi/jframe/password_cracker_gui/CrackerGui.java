package jframe.password_cracker_gui;

import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import threads.password_cracker.CrackerRunnable;
import threads.password_cracker.Safe;

public class CrackerGui extends JFrame {
    Safe safe;
    JTextField inputPassword;
    SafePanel safePanel;
    JButton startBtn;
    CrackerRunnable[] crackers;

    private class SafePanel extends JPanel {
        JLabel[] codeLabels;

        public SafePanel() {
            super();
            this.setLayout(null);
            this.codeLabels = new JLabel[crackers.length];
            for (int i = 0; i < this.codeLabels.length; i++) {
                this.codeLabels[i] = new JLabel("------");
                codeLabels[i].setBounds(110, 150 + i * 20, 80, 20);
                codeLabels[i].setBorder(new BevelBorder(0));
                this.add(codeLabels[i]);
            }

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawRect(100, 100, 100, 200);
            // g.drawRoundRect(110, 150, 80, 20, 5, 5);
        }

        public void updateGuesses() {
            for (int i = 0; i < crackers.length; i++) {
                this.codeLabels[i].setText(crackers[i].getGuess());
            }
        }

    }

    public CrackerGui(Safe safe, CrackerRunnable[] cracker) {
        super();

        this.safe = safe;
        this.crackers = cracker;
        this.setTitle("Password Cracker");
        this.setSize(1080, (int) (1080 / 1.6));
        this.safePanel = new SafePanel();
        this.add(safePanel);

    }

    @Override
    public void paint(Graphics g) {
        this.safePanel.updateGuesses();
        super.paint(g);
    }
}
