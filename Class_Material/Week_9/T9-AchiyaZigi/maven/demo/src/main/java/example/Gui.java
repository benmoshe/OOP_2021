package example;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

public class Gui extends JFrame {
    private class DotPanel extends JPanel {
        private List<Point> points;

        public DotPanel() {

            points = new ArrayList<>();
            this.addMouseListener(new MouseInputAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    points.add(new Point(e.getX(), e.getY()));
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int r = 5;
            for (Point p : points) {
                g.drawOval(p.x - r, p.y - r, r * 2, r * 2);
            }
        }
    }

    private DotPanel dp;

    public Gui() {
        dp = new DotPanel();
        this.add(dp);
    }
}
